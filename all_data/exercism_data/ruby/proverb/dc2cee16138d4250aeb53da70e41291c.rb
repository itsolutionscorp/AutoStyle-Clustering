class Proverb

  def initialize(*items, qualifier: nil)
    @items = items
    @qualifier = qualifier
  end

  def to_s
    s = ""
    prev = nil
    @items.each do |item|
      s += "For want of a #{prev} the #{item} was lost.\n" if prev
      prev = item
    end
    if @qualifier
      s += "And all for the want of a #{@qualifier} #{@items.first}."
    else
      s += "And all for the want of a #{@items.first}."
    end
  end
end
