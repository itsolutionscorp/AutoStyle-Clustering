# rubocop:disable Documentation
class Element
  def initialize(datum, next_node)
    @datum = datum
    @next = next_node
  end

  attr_reader :datum, :next

  def to_a
    Element.to_a(self)
  end

  def reverse(nxt = nil)
    node = Element.new(datum, nxt)
    @next ? @next.reverse(node) : node
  end

  def self.from_a(enumerable)
    current_node = nil

    Array(enumerable).reverse.each do |x|
      current_node = Element.new(x, current_node)
    end

    current_node
  end

  def self.to_a(element)
    return [] unless element
    [element.datum] + to_a(element.next)
  end
end
