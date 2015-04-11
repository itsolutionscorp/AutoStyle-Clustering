class Deque

  def push data
    @last = build data
  end

  def pop
    unwire @last
    data, @last = @last.data, @last.parent
    data
  end

  def unshift data
    @first = build data
  end

  def shift
    unwire @first
    data, @first = @first.data, @first.child
    data
  end

  private

  def build data
    elem = Element.new(data, @first, @last)
    @first ||= elem
    @last ||= elem
    @last.child, @first.parent = [elem] * 2
    elem
  end

  def unwire elem
    elem.parent.child = elem.parent
    elem.child.parent = elem.child
  end

  private

  class Element < Struct.new(:data, :parent, :child); end

end
