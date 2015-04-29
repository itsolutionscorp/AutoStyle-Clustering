class Proverb
  def initialize(*items)
    @items = items
    @qualifier = qualifier
  end

  def to_s
    proverb = ''
    (items.size-1).times { |count| proverb += consequence count }
    proverb += last_special_consequence
  end

  private

  attr_reader :items

  def consequence count
    "For want of a #{items[count]} the #{items[count+1]} was lost.\n"
  end

  def last_special_consequence
    "And all for the want of a #{@qualifier}#{items[0]}."
  end

  def qualifier
    last_item_as_hash? ? items.pop[:qualifier] + ' ' : ''
  end

  def last_item_as_hash?
    items.last.is_a?(Hash)
  end
end
