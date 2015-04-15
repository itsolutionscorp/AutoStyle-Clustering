class Deque
  def push(value)
    element = wrap(value)
    element.follow(last_element) if last_element
    self.first_element = element unless first_element
  end

  def pop
    element = last_element

    if element.single?
      self.first_element = nil
    else
      new_last_element = last_element.prev
      first_element.follow(new_last_element)
    end

    element.value
  end

  def shift
    element = first_element

    if element.single?
      self.first_element = nil
    else
      new_first_element = first_element.next
      last_element.precede(new_first_element)
      self.first_element = new_first_element
    end

    element.value
  end

  def unshift(value)
    element = wrap(value)
    element.precede(first_element) if first_element
    self.first_element = element
  end

  private

  attr_accessor :first_element

  def last_element
    first_element && first_element.prev
  end

  def wrap(value)
    Element.new(value)
  end
end


class Element
  attr_accessor :prev, :next
  attr_reader :value

  def initialize(value)
    @value = value
    self.next = self.prev = self
  end

  def precede(other)
    other.prev.next = self
    self.prev, self.next, other.prev = other.prev, other, self
  end

  def follow(other)
    other.next.prev = self
    other.next, self.next, self.prev = self, other.next, other
  end

  def single?
    self == self.next
  end
end
