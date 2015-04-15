class Deque
  def push(value)
    if @head
      old_tail = @head.prev_element
      new_tail = Element.new(value)
      link(new_tail, @head)
      link(old_tail, new_tail)
    else
      create_head(value)
    end
  end

  def pop
    old_tail = @head.prev_element
    value = old_tail.value
    new_tail = old_tail.prev_element
    link(new_tail, @head)
    value
  end

  def shift
    new_head = @head.next_element
    value = @head.value
    tail = @head.prev_element
    link(tail, new_head)
    @head = new_head
    value
  end

  def unshift(value)
    if @head
      old_tail = @head.prev_element
      new_head = Element.new(value)
      link(old_tail, new_head)
      link(new_head, @head)
      @head = new_head
    else
      create_head(value)
    end
  end

  private

  def create_head(value)
    @head = Element.new(value)
    @head.next_element = @head
    @head.prev_element = @head
  end

  def link(first, second)
    first.next_element = second
    second.prev_element = first
  end
end

class Element
  attr_accessor :next_element, :prev_element
  attr_reader :value

  def initialize(value)
    @value = value
  end
end
