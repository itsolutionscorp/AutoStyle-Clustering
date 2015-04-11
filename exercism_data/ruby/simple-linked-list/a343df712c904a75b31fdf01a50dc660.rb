class Element
  class << self
    def to_a(element)
       return [] if element.nil?
      a = [element.datum]
      until element.next.nil?
        element = element.next
        a << element.datum
      end
      a  
    end

    def from_a(elements)
      return nil if elements.nil?
      elements = elements.to_a
      prev_node = nil
      until elements.empty?
        node = new(elements.pop, prev_node)
        prev_node = node
      end
      node
    end
  end

  attr_reader :datum, :next

  def initialize(value, next_node)
    @datum, @next = value, next_node
  end

  def reverse
    return self if @next.nil?
    other_el = self.class.new(datum, @next.next)
    self.class.new(@next.datum, other_el)
  end

  def to_a
    self.class.to_a(self)
  end

end
