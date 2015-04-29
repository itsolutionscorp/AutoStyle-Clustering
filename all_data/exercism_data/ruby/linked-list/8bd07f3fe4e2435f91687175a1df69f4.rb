Node = Struct.new(:value, :next, :prev)

class Deque
  def initilize
    @head = nil
    @tail = nil
  end

  def pop
    tmp = @tail
    @tail = tmp.prev
    tmp.value
  end

  def shift
    tmp = @head
    @head = tmp.next
    tmp.value
  end

  def add(value)
    new_node = Node.new(value, nil, nil)
    if @head.nil?
      @head = new_node
      @tail = new_node
    else
      yield(new_node)
    end
  end

   def push(value)
    add(value) do |new_tail|
      old_tail = @tail
      old_tail.next = new_tail
      @tail = new_tail
      new_tail.prev = old_tail
    end
  end

  def unshift(value)
    add(value) do |new_head|
      old_head = @head
      old_head.prev = new_head
      @head = new_head
      new_head.next = old_head
    end
  end

end
