class Deque

  def push(item)
    @head = Cell.new(item, nil, @head)
    @head.next.prev = @head if @head.next
    @tail = @head unless @tail
  end

  def pop
    value = @head.value
    @head = @head.next
    @head.prev = nil if @head
    return value
  end

  def unshift(item)
    @tail = Cell.new(item, @tail, nil)
    @tail.prev.next = @tail if @tail.prev
    @head = @tail unless @head
  end

  def shift
    value = @tail.value
    @tail = @tail.prev
    @tail.next = nil if @tail
    return value
  end

end

class Cell
  attr_accessor :value, :next, :prev

  def initialize(value, prev, nxt)
    @value, @prev, @next = value, prev, nxt
  end
end
