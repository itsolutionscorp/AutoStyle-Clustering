class Proverb

  def initialize(*items)
    @proverb = make_proverb extract_items(items), extract_qualifier(items)
  end

  def to_s
    @proverb
  end

  private

  def make_proverb(items, qualifier)
    proverb = ""

    items.each_with_index do |item, indx|
      if indx > 0
        proverb += "For want of a #{items[indx - 1]} the #{items[indx]} was lost.\n"
      end
    end

    proverb += "And all for the want of a #{format_qualifier(qualifier)}#{items[0]}."

    proverb
  end

  def format_qualifier(qualifier)
    unless qualifier.nil?
      "#{qualifier} "
    else
      ""
    end
  end

  def extract_items(items)
    if items.last.kind_of? Hash
      items[0..items.count - 2]
    else
      items
    end
  end

  def extract_qualifier(items)
    if items.last.kind_of? Hash
      items.last[:qualifier]
    end
  end

end
