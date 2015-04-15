class Element

  attr_accessor :datum, :next

  def initialize(datum, nexxt = nil)
    @datum = datum
    @next = nexxt
  end

  def self.to_a elem
    elem.to_a
  end

  def self.from_a array
    a = array.to_a.map { |e| Element.new(e) }
    a.each_cons(2) { |x, y| x.next = y }
    a.first
  end

  def to_a
    [@datum, @next.to_a].compact.flatten
  end

  def reverse
    @next ? Element.new(@next.datum, self) : self
  end

end
