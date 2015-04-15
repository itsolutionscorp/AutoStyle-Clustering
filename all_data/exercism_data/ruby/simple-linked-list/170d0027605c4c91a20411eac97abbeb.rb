class Element
  attr_accessor :datum, :next

  def initialize(datum, node)
    @datum = datum
    @next = node
  end

  def reverse
    result = []
    element = self
    while element
      result << element
      element = element.next
    end

    old_element = nil

    all = result.map.with_index do |element, index|
      new_element = Element.new(
        element.datum,
        old_element
      )
      old_element = new_element
    end

    all.last
  end

  def to_a
    result = []
    element = self

    if element && element.next
      result << element.datum
      result + element.next.to_a
    elsif element
      result << element.datum
    else
      result
    end
  end

  def self.to_a(element)
    element.to_a
  end

  def self.from_a(list)
    list = list.to_a
    return if list.empty?
    old_element = nil

    list.reverse.map do |data|
      current_element = Element.new(
        data,
        old_element
      )
      old_element = current_element
    end.last
  end
end
