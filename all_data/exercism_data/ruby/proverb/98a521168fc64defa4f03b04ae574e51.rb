class Proverb
  attr_reader :items, :qualifier

  def initialize(*args)
    @options   = args.last.is_a?(Hash) ? args.pop : {}
    @qualifier = @options[:qualifier]
    @items     = Array(args)
  end

  def to_s
    item_messages + final_message
  end

  private

  def item_messages
    item_pairs.each_with_object("") do |(item, next_item), message|
      next unless next_item
      message << "For want of a #{item} the #{next_item} was lost.\n"
    end
  end

  def item_pairs
    pairs = {}
    items.each_with_index { |item, index| pairs[item] = items[index + 1] }
    pairs
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
