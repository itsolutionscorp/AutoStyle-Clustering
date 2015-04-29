class Deque
  def push(element)
    self.last = Element.new(element, last, nil)
    validate_first_object
  end

  def unshift(element)
    self.first = Element.new(element, nil, first)
    validate_first_object
  end

  def pop
    element = last || NIL_ELEMENT
    self.last = element.prev 
    validate_last_object
    element.object
  end

  def shift
    element = first || NIL_ELEMENT
    self.first = element.next
    validate_last_object
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

  def validate_first_object
    @first = @last if first.nil?
    @last = @first if last.nil?
  end

  def validate_last_object
    @first = nil if last.nil?
    @last = nil if first.nil?
  end
end
