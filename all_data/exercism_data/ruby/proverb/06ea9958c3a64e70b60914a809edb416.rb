class Proverb
  def initialize(*items, qualifier: nil)
    @items     = items
    @qualifier = qualifier
  end

  def to_s
    events.join("\n") + "\nAnd all for the want of a #{catalyst}."
  end

  private

  attr_reader :items, :qualifier

  def catalyst
    [qualifier, items.first].compact.join(' ')
  end

  def events
    items.each_cons(2).map { |a, b| "For want of a #{a} the #{b} was lost." }
  end
end
