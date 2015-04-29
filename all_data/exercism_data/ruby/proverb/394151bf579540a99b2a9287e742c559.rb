class Proverb
  def initialize(*items, qualifier: nil)
    @items     = items
    @qualifier = qualifier
  end

  def to_s
    [body, coda].join("\n")
  end

  private

  attr_reader :items, :qualifier

  def body
    links.join("\n")
  end

  def coda
    "And all for the want of a #{catalyst}."
  end

  def catalyst
    [qualifier, items.first].compact.join(' ')
  end

  def links
    items.each_cons(2).map { |a, b| "For want of a #{a} the #{b} was lost." }
  end
end
