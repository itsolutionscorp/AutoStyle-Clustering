class Proverb

  def initialize(*items, qualifier: nil)
    @items = items
    @qualifier = qualifier
  end

  def to_s
    body + ending
  end

  def body
    s = ""
    prev = nil
    @items.each do |item|
      s += "For want of a #{prev} the #{item} was lost.\n" if prev
      prev = item
    end
    s
  end

  def ending
    if @qualifier
      "And all for the want of a #{@qualifier} #{@items.first}."
    else
      "And all for the want of a #{@items.first}."
    end
  end
end
