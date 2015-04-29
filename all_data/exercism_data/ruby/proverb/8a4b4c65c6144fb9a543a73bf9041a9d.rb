class Proverb

  def initialize(*items, qualifier: nil)
    @items = items
    @qualifier = qualifier
  end

  def to_s
    body + ending
  end

  private

  def body
    s = ""
    @items.each_cons(2) do |wanted_item, lost_item|
      s += "For want of a #{wanted_item} the #{lost_item} was lost.\n"
    end
    s
  end

  def ending
    s = "And all for the want of a "
    s += "#{@qualifier} " if @qualifier
    s += "#{@items.first}."
  end
end
