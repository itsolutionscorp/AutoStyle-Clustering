class Element
  attr_reader :datum, :next

  def initialize(datum, n)
    @datum = datum
    @next = n
  end

  def reverse
    vals = to_a.reverse
    Element.from_a(vals)
  end

  def to_a
    e = self
    res = [e.datum]
    res << e.datum while (e = e.next)
    res
  end

  def self.to_a(e)
    e.to_a
  end

  def self.from_a(vals)
    last = nil
    res = []
    vals.reverse_each do |val|
      el = Element.new(val, last)
      res << el
      last = el
    end
    res[-1]
  end
end
