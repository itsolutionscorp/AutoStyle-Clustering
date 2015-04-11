class Element
  attr_accessor :datum, :next

  def initialize(datum, node)
    @datum = datum
    @next = node
  end

  def reverse
    old_element = nil

    all = collect_all_nodes.map.with_index do |element, index|
      new_element = Element.new(
        element.datum,
        old_element
      )
      old_element = new_element
    end

    all.last
  end

  def to_a
    collect_all_node_data
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

  private

  def collect_all_nodes
    result = []
    element = self
    while element
      result << element
      element = element.next
    end
    result
  end

  def collect_all_node_data
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
end
