class Element
  attr_reader :datum, :next

  def initialize(datum, next_element)
    @datum, @next = datum, next_element
  end

  def to_a
    [datum].push *self.next.to_a
  end


  def reverse
    self.class.create_multiple(to_a)
  end

  class << self
    def to_a(element)
      element.to_a
    end

    def from_a(list)
      create_multiple(list.to_a.reverse)
    end

    def create_multiple(elements)
      elements.inject(nil) do |head, datum| 
        new(datum, head)
      end
    end
  end
end
