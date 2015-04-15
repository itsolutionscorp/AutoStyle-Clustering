class Proverb
  def initialize(*items)
    @qualifier = extract_options!(items)[:qualifier]
    @items = items
  end

  def to_s
    [build_lines, last_line].flatten.join("\n")
  end

  private

  def build_lines
    @items[0..-2].each_with_index.map do |item, i|
      "For want of a #{item} the #{@items[i+1]} was lost."
    end
  end

  def last_line
    "And all for the want of a #{last_item}."
  end

  def last_item
    [@qualifier, @items.first].compact.join(' ')
  end

  def extract_options!(args)
    args.last.is_a?(::Hash) ? args.pop : {}
  end
end

puts Proverb.new('nail', 'shoe')
