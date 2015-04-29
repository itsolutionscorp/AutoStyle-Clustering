class Deque
  def push(value)
    assign(Element.new(value, last, nil)) do |element, _, last|
      self.last = last.next = element
    end
  end

  def unshift(value)
    assign(Element.new(value, nil, first)) do |element, first, _|
      self.first = first.prev = element
    end
  end

  def pop
    last.tap do |element|
      self.last = element.prev
      last.next = nil if last
    end.value
  end


  def shift
    first.tap do |element|
      self.first = element.next
      first.prev = nil if first
    end.value
  end

  private

  attr_accessor :first, :last

  def assign(element)
    if first && last
      yield element, first, last
    else
      self.first = self.last = element
    end
  end
end

class Element < Struct.new(:value, :prev, :next); end
