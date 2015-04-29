class Element
  attr_reader :datum, :next

  def initialize(datum, next_element = nil)
    @datum, @next = datum, next_element
  end

  def each_element
    element = self
    until element.nil?
      yield element
      element = element.next
    end
  end

  def reverse
    self.class.reverse(self)
  end

  def to_a
    self.class.to_a(self)
  end

  def self.reverse(element)
    return nil if element.nil?

    reversed = nil
    element.each_element { |elem| reversed = new(elem.datum, reversed) }

    reversed
  end

  def self.to_a(element)
    return [] if element.nil?

    [].tap do |arr|
      element.each_element { |elem| arr << elem.datum }
    end
  end

  def self.from_a(data)
    data = data.to_a
    data.reverse.each.inject(nil) { |element, datum| new(datum, element) }
  end
end
