package kr.frec.week1.userManager

class UserManager(
    var usersCapacity: Int
) {
    private var users: MutableMap<String, User> = HashMap<String, User>()
    var currentUser: User? = null

    fun size(): Int {
        return this.users.size
    }

    fun login(id: String, pw: String): Boolean {
        val user = this.users[id] ?: return false

        if (user valid pw) {
            this.currentUser = user
            return true
        }
        return false
    }

    fun logout() { this.currentUser = null }

    fun newUser(id: String, name: String, pw: String): Boolean {
        if (this.users.size >= this.usersCapacity) return false
        this.users.putIfAbsent(id, User(id, name, pw)) ?: return true
        return false
    }

    fun getUserById(id: String): User? {
        return this.users[id]
    }

    override fun toString(): String {
        return "UserManager<usersCapacity: $usersCapacity, \n  currentUser: $currentUser,\n  users: $users>"
    }
}