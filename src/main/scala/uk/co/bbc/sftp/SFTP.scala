package uk.co.bbc.sftp

import java.io.File

import org.apache.commons.vfs2.auth.StaticUserAuthenticator
import org.apache.commons.vfs2.impl.DefaultFileSystemConfigBuilder
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder
import org.apache.commons.vfs2.{FileObject, FileSystemOptions, UserAuthenticator, VFS}

/** Facade for Apache's VFS SFTP client.
 *
 * @param username the username
 * @param password the password
 * @param sshPrivateKeyFile the private SSH key file to use for SSH encryption
 */
class SFTP(username: String, password: String, sshPrivateKeyFile: File) {
  private val vfsManager = VFS.getManager
  private val authenticator: UserAuthenticator = new StaticUserAuthenticator(
    SFTP.NoDomain,
    username,
    password
  )
  private val options: FileSystemOptions = {
    val _options = new FileSystemOptions
    SftpFileSystemConfigBuilder.getInstance().setIdentities(_options, Array(sshPrivateKeyFile))
    DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(_options, authenticator)
    _options
  }

  /** @see  org.apache.commons.vfs2.FileSystemManager.resolveFile */
  def resolveFile(uri: String): FileObject = vfsManager.resolveFile(uri, options)
}

private object SFTP {
  private val NoDomain: String = null
}
