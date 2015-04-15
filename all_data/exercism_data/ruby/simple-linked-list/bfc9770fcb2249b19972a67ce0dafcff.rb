class Element
  attr_reader :datum, :succ

  def initialize(data, succ)
    @datum = data
    @succ = succ
  end

  def next
    succ
  end

  def next=(other)
    @succ = other
  end

  def reverse
    Element.from_a(to_a.reverse)
  end

  def to_a
    return [] if datum.nil?
    [datum, succ.to_a].flatten
  end

  def self.from_a(arr)
    arr = arr.to_a
    return nil if arr.empty?
    Element.new(arr.shift, Element.from_a(arr))
  end

  def self.to_a(node)
    return [] if node.nil?
    node.to_a
  end
end
