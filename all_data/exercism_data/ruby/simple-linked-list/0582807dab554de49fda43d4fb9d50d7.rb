class Element
  attr_reader :datum, :next
  def initialize *args
    @datum = args[0]
    @next = args[1]
  end

  def self.to_a el
    el.to_a
  end

  def self.from_a arr
    arr = arr.to_a
    return nil if arr.empty?
    Element.new(arr[0], from_a(arr[1..-1]))
  end

  def to_a
    [@datum, @next.to_a].flatten
  end

  def reverse
    @next ? Element.new(@next.datum, self) : self
  end
end
