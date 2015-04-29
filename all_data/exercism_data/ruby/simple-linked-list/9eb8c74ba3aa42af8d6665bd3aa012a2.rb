class Element
  def self.to_a(element)
    Array(element)
  end

  def self.from_a(array)
    array = Array(array)

    return if array.empty?

    new(array.shift, from_a(array))
  end

  attr_reader :datum, :next

  def initialize(datum, next_)
    @datum, @next = datum, next_
  end

  def to_a
    [datum] + Array(self.next)
  end

  def reverse
    self.class.from_a(to_a.reverse)
  end
end
