class Element
  attr_reader :datum
  attr_accessor :next

  def initialize(datum, pointer)
    @datum = datum
    @next = pointer
  end

  def to_a
    current = [datum]
    if self.next
      rest = self.next.to_a
      current.concat(rest)
    else
      current
    end
  end

  def reverse
    data = self.class.to_a(self).reverse
    self.class.from_a(data)
  end

  def self.to_a(node)
    return [] unless node
    node.to_a
  end

  def self.from_a(array)
    array = Array(array)
    return if array.none?
    first_node = self.new(array.first, nil)
    previous_node = first_node
    array[1..-1].each do |datum|
      new_node = self.new(datum, nil)
      previous_node.next = new_node
      previous_node = new_node
    end
    first_node
  end
end
