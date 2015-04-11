class Element
  attr_reader :datum, :next

  def initialize(datum, next_element)
    @datum, @next = datum, next_element
  end

  def self.to_a(from_element)
    out = []

    element = from_element
    while element
      out << element.datum
      element = element.next
    end

    out
  end

  def self.from_a(array)
    array.to_a.reverse.inject(nil) { |last_built_element, value|
      new(value, last_built_element)
    }
  end

  def to_a
    self.class.to_a(self)
  end

  def reverse
    self.class.from_a(to_a.reverse)
  end
end
