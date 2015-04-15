class Proverb

  attr_reader :items
  attr_reader :qualifier

  def initialize(*args)
    @items = args
    @qualifier = extract_qualifier
  end

  def to_s
    @proverb ||= generate_proverb 
  end

  private

  def generate_proverb
    lines = items.each_with_index.each_with_object([]) do |(item,index), full_text|
      next_item = items[index + 1]
      full_text << "For want of a #{item} the #{next_item} was lost." if next_item
    end
    lines.join("\n") + "\nAnd all for the want of a #{qualifier}#{items.first}."
  end

  def extract_qualifier
    return '' unless items.last.is_a?(Hash) && items.last[:qualifier]
    items.pop[:qualifier] + ' '
  end

end
