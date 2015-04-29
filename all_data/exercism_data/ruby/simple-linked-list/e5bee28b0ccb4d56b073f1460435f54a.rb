class Element
  attr_reader :datum, :next
  def initialize(datum, next_element = nil)
    @datum = datum
    @next = next_element
  end

  def self.to_a(elem)
    arr = []
    each_datum(elem, &arr.method(:push))
    arr
  end

  def self.each_datum(elem)
    until elem.nil?
      yield elem.datum
      elem = elem.next
    end
  end

  def to_a
    self.class.to_a(self)
  end

  def self.reverse(elem)
    res = nil
    each_datum(elem) { |datum| res = new(datum, res) }
    res
  end

  def reverse
    self.class.reverse(self)
  end

  def self.from_a(arr)
    arr.reverse_each.inject(nil) { |memo, obj| new(obj, memo) }
  end
end
