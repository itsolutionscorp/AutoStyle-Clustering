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
    @items.each_cons(2) do |items|
      s += "For want of a #{items[0]} the #{items[1]} was lost.\n"
    end
    s
  end

  def ending
    s = "And all for the want of a "
    s += "#{@qualifier} " if @qualifier
    s += "#{@items.first}."
  end
end
