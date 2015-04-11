class Proverb
  attr_reader :items, :qualifier

  def initialize(*items, qualifier: nil)
    @items     = Array(items)
    @qualifier = qualifier
  end

  def to_s
    [item_messages, final_message].join("\n")
  end

  private

  def item_messages
    item_pairs.map do |missing_item, lost_item|
      "For want of a #{missing_item} the #{lost_item} was lost."
    end
  end

  def item_pairs
    items.each_cons(2)
  end

  def final_message
    "And all for the want of a #{qualified_primary_item}."
  end

  def qualified_primary_item
    qualifier ? "#{qualifier} #{primary_item}" : primary_item
  end

  def primary_item
    items.first
  end
end
