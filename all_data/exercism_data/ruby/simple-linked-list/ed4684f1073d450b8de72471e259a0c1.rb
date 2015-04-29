class Element
  class << self
    def to_a(arg)
      Array(arg)
    end

    def from_a(ary)
      head, *tail = Array(ary)
      return NullElement.new unless head
      new(head, from_a(tail))
    end
  end

  attr_reader :datum, :_next
  alias_method :next, :_next

  def initialize(datum, _next = nil)
    @datum, @_next = datum, (_next || NullElement.new)
  end

  def to_a
    [datum] + _next.to_a
  end

  def reverse
    _next.reverse.concat(Element.new(datum))
  end

  def concat(list)
    Element.new(datum, _next.concat(list))
  end
end

class NullElement
  def to_a
    []
  end

  def reverse
    self
  end

  def nil?
    true
  end

  def next
    nil
  end

  def concat(list)
    list
  end
end
