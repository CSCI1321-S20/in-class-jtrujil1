package mud

class Player(val name: String, private var inventory: List[Item], position: Room) {

    def processCommand(command: String): Unit = ???

    def getFromInventory(itemName: String): Option[Item] =
            inventory.find(_.name.toLowerCase == itemName.toLowerCase) match {
            case Some(item) =>
            items = items.filter(_ != item)//use patch instead to not drop everything that is the same
            dropItem()
            printf("\n%s dropped in %s.", itemName, this.position.name)
            Some(item)
            case None =>
            printf("\nThere is no %s in inventory.", itemName)
            None
        }

    def addToInventory(item: Item): Unit = {
        if(getItem(item) != None){
            getItem(item)::inventory
            printline
            printf("\n%s has been added to your inventory.", item)
        }else{
            println("That is not a valid item.")
        }
    }

    def inventoryListing(): String = {
        printf("\nInventory (total items: %d)", inventory.length)
        for(i <- 0 until inventory.length){
                println(inventory(i).name + " " + inventory(i).desc)
        }
    }

    def move(dir: String): Unit = ??
}

object Player {

}