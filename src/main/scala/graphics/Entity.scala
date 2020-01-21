package graphics

class Entity(val x: Double, val y: Double) {
    def width: Double = 1
    def height: Double = 1

    def intersects(other: Entity): Boolean = {
        val overlapX = (x - other.x).abs < (width + other.width)/2
        val overlapY = (y - other.y).abs < (height + other.height)/2
        overlapX && overlapY
    }
}