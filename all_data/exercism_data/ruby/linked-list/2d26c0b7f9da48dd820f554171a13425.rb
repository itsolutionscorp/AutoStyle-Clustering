class Deque
  def initialize()
    @head = nil
    @tail = nil
  end

  def push(item)
    new_element = Element.new(item, nil, @tail)
    @tail.next_element = new_element unless @tail.nil?
    @tail = new_element
    @head = @tail if @head.nil?
  end

  def pop
    popped_element = @tail
    @tail = popped_element.prev_element
    @head = nil if @tail.nil?
    popped_element.data
  end

  def shift
    shifted_element = @head
    @head = shifted_element.next_element
    @tail = nil if @head.nil?
    shifted_element.data
  end

  def unshift(item)
    new_element = Element.new(item, @head, nil)
    @head.prev_element = new_element unless @head.nil?
    @head = new_element
    @tail = @head if @tail.nil?
  end


end

class Element
  attr_accessor :data, :next_element, :prev_element

  def initialize(data, next_element, prev_element)
    @data = data
    @next_element = next_element
    @prev_element = prev_element
  end
end
