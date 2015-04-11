class Element
  attr_reader :datum, :next

  def self.to_a(obj)
    obj.to_a
  end

  def self.from_a(obj)
    first, *rest = obj.to_a
    new(first, from_a(rest)) if first
  end

  def initialize(datum, obj)
    @datum = datum
    @next = obj
  end

  def to_a
    [datum, *self.next.to_a]
  end

  def reverse(obj = nil)
    self.next ? self.next.reverse(self) : self.class.new(datum, obj)
  end
end
