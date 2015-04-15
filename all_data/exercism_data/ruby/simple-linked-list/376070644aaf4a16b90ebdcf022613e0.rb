class Element
  attr_accessor :datum, :link

  def initialize(datum, link)
    @datum = datum
    @link = link
  end

  def next
    link
  end

  def reverse
    if link
      Element.new(link.datum, self)
    else
      Element.new(datum, nil)
    end
  end

  def self.to_a(node)
    return [] unless node

    node_datum = Array(node.datum)
    node_datum += Element.to_a(node.link) if node.link
    node_datum
  end

  def to_a
    Element.to_a(self)
  end

  def self.from_a(array)
    data = array.to_a
    return nil if data.empty?

    results = []
    data.reverse.each_with_index do |element, i|
      results[i] = Element.new(element, results[i - 1])
    end
    results.last
  end

end
