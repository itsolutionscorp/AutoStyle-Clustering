class Deque
  def push(value)
    element = wrap(value)
    element.follow(@last_element) if @last_element
    @first_element ||= element
    @last_element = element
  end

  def pop
    element = @last_element
    @last_element = @last_element.prev
    @first_element = nil unless @last_element
    element.value
  end

  def unshift(value)
    element = wrap(value)
    element.precede(@first_element) if @first_element
    @last_element ||= element
    @first_element = element
  end

  def shift
    element = @first_element
    @first_element = @first_element.next
    @last_element = nil unless @first_element
    element.value
  end

  private

  def wrap(value)
    Element.new(value)
  end
end


class Element
  attr_accessor :prev, :next
  attr_reader :value

  def initialize(value)
    @value = value
  end

  def precede(other)
    other.prev.next = self if other.prev
    self.prev, self.next, other.prev = other.prev, other, self
  end

  def follow(other)
    other.precede(self)
  end
end
