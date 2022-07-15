package todolist

object UserSession {
    private var users = mutableMapOf<String, User>()
    private var currentUser: User? = null

    fun newUser(name: String, password: String) {
        val user = User(name, password)
        users[name] = user
    }

    fun loginUser(name: String, password: String) {
        when (name) {
            in users.keys -> {
                val user = users[name]
                if (user != null) {
                    if (user.checkPassword(password)) {
                        currentUser = user
                    } else {
                        throw LoginFailedException("Invalid Password.")
                    }
                } else {
                    throw LoginFailedException("No Logged-In User.")
                }
            }
            else -> throw LoginFailedException("No Signed-Up User.")
        }
    }
}

class LoginFailedException(message: String) : Exception(message) {
    override val message: String
        get() = "Login Failed: " + super.message
}