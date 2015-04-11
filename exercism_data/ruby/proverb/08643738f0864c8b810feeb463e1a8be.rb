class Proverb

  attr_reader :items

  def initialize(*items)
    find_qualifier(items[-1])
    create_proverb_list(items)
  end

  def to_s
    phrase(items)
  end

  private

  def find_qualifier(option)
    @qualifier = option[:qualifier] + ' ' if option.class == Hash
  end

  def create_proverb_list(items)
    @qualifier ? @items = items[0..-2] : @items = items
  end

  def phrase(item_list)
    item_list.length > 1 ? proverb_lines(item_list) : final_line
  end

  def proverb_lines(item_list)
    single_line(item_list) + phrase(item_list[1..-1])
  end

  def single_line(items)
    "For want of a #{items[0]} the #{items[1]} was lost.\n"
  end

  def final_line
    if @qualifier
      "And all for the want of a #{@qualifier + items[0]}."
    else
      "And all for the want of a #{items[0]}."
    end
  end

end
