class Proverb
  def initialize(*items)
    @options = items.last.is_a?(Hash) ? items.last : {}
    @items   = items.last.is_a?(Hash) ? items[0..-2] : items
  end

  def to_s
    lines.join("\n") + ending
  end

  private
  attr_reader :items, :options

  def qualifier
    options[:qualifier]
  end

  def wanted
    [qualifier, items.first].compact.join(' ')
  end

  def ending
    "\nAnd all for the want of a #{wanted}."
  end

  def lines
    items.each_cons(2).map { |a, b| "For want of a #{a} the #{b} was lost." }
  end

end
