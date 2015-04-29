class Proverb

  def initialize(*items)
    @proverb = make_proverb extract_items(items), extract_qualifier(items)
  end

  def to_s
    @proverb
  end

  private

  def make_proverb(items, qualifier)
    make_small_actions(items) + make_large_consequence(qualifier, items[0])
  end

  def make_small_actions(items)
    small_actions = ""
    items.each_with_index do |item, indx|
      small_actions += make_small_action(items[indx - 1], items[indx]) if past_first_item? indx
    end
    small_actions
  end

  def make_small_action(item_before, item)
    "For want of a #{item_before} the #{item} was lost.\n"
  end

  def make_large_consequence(qualifier, original_item)
    "And all for the want of a #{format_qualifier(qualifier)}#{original_item}."
  end

  def past_first_item?(indx)
    indx > 0
  end

  def format_qualifier(qualifier)
    qualifier.nil? ? "" : "#{qualifier} "
  end

  def extract_items(items)
    items.last.kind_of?(Hash) ? items[0..items.count - 2] : items
  end

  def extract_qualifier(items)
    items.last.kind_of?(Hash) ? items.last[:qualifier] : nil
  end

end
