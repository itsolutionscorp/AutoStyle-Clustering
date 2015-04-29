class Proverb
  attr_reader :items, :qualifier
  def initialize(*items, qualifier: nil)
    @items = items
    @qualifier = qualifier
  end

  def to_s
    stanzas = items.each_cons(2).map { |wanted, lost| stanza(wanted, lost) }
    stanzas << "And all for the want of a #{final_item}."
    stanzas.join("\n")
  end

private
  def stanza(wanted, lost)
    "For want of a #{wanted} the #{lost} was lost."
  end

  def final_item
    [qualifier, items.first].compact.join(' ')
  end
end
