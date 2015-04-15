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
    element.value
  end

  def shift
    element = @first_element
    @first_element = @first_element.next
    element.value
  end

  def unshift(value)
    element = wrap(value)
    element.precede(@first_element) if @first_element
    @last_element ||= element
    @first_element = element
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
    other.prev = self
    self.next = other
  end

  def follow(other)
    other.precede(self)
  end
end
