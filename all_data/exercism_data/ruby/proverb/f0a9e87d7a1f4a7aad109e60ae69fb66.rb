class Proverb
  def initialize(*items, qualifier: nil)
    @qualifier = qualifier
    @items = items
  end

  def to_s
    middle_lines + last_line
  end

  def middle_lines
    @items[1..-1].map.with_index do |item, index_of_previous_item|
      "For want of a #{@items[index_of_previous_item]} the #{item} was lost.\n"
    end.join
  end

  def last_line
    "And all for the want of a #{item_with_qualifier}."
  end

  def item_with_qualifier
    [@qualifier, @items.first].compact.join(" ")
  end
end
