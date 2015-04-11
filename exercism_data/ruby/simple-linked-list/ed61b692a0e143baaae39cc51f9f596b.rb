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
    result = []
    while node && node.datum
      result << node.datum
      node = node.link
    end
    result
  end

  def to_a
    Element.to_a(self)
  end

  def self.from_a(data)
    return nil unless data

    data = format(data)
    data.each_with_object([]).with_index do |(element, results), i|
      results[i] = Element.new(element, results[i - 1])
    end
    .last
  end

  private

  def format(data)
    data.to_a.reverse
  end
end
