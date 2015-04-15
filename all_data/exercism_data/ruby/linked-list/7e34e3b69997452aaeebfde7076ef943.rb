class Deque
  def push(object)
    self.last = Element.new(object, last, nil)
    validate_first_element
  end

  def unshift(object)
    self.first = Element.new(object, nil, first)
    validate_first_element
  end

  def pop
    element = last || NIL_ELEMENT
    self.last = element.prev 
    validate_last_element
    element.object
  end

  def shift
    element = first || NIL_ELEMENT
    self.first = element.next
    validate_last_element
    element.object
  end

private

  Element = Struct.new(:object, :prev, :next)

  NIL_ELEMENT = Element.new(nil, nil, nil)

  attr_reader :first, :last

  def first=(new_first)
    @first.prev = new_first if @first
    @first = new_first
  end

  def last=(new_last)
    @last.next = new_last if @last
    @last = new_last
  end

  def validate_first_element
    self.first = last if first.nil?
    self.last = first if last.nil?
  end

  def validate_last_element
    self.first = nil if last.nil?
    self.last = nil if first.nil?
  end
end
