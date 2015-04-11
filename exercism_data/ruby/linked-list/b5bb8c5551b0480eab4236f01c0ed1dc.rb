class Deque
  Element = Struct.new(:object, :prev, :next)

  def push(element)
    new_element = Element.new(element, last, nil)
    self.last = new_element
  end

  def pop
    element = last
    self.last = element.prev 
    element.object
  end

  def unshift(element)
    new_element = Element.new(element, nil, first)
    self.first = new_element
  end

  def shift
    element = first
    self.first = element.next
    element.object
  end

private

  def first=(new_first)
    @first.prev = new_first if @first
    @first = new_first 
    @last = @first unless @last
  end

  def last=(new_last)
    @last.next = new_last if @last
    @last = new_last
    @first = @last unless @first
  end

  def last
    @last || Element.new(nil, nil, nil)
  end

  def first
    @first || Element.new(nil, nil, nil)
  end

end
