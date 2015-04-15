class Proverb
  attr_reader :items, :original_desire

  def initialize(*wanted_items, qualifier: nil)
    @items = wanted_items
    @original_desire = (Array(qualifier) << @items.first).join(' ')
  end

  def to_s
    (item_phrases + [ending_phrase]).join("\n")
  end

private

  def item_phrases
    items.each_cons(2).map(&method(:pair_phrase))
  end

  def pair_phrase((wanted, lost))
    "For want of a #{wanted} the #{lost} was lost."
  end

  def ending_phrase
    "And all for the want of a #{original_desire}."
  end
end
