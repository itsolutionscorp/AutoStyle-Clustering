class Proverb
  def initialize(*items, qualifier: nil)
    @items     = items
    @qualifier = qualifier
  end

  def to_s
    (lines + [ending]).join("\n")
  end

  private

  attr_reader :items, :qualifier

  def wanted
    [qualifier, items.first].compact.join(' ')
  end

  def ending
    "And all for the want of a #{wanted}."
  end

  def lines
    items.each_cons(2).map { |a, b| "For want of a #{a} the #{b} was lost." }
  end
end
