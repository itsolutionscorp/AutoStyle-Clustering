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
    @items.each_cons(2).each_with_object("") do |(wanted_item, lost_item), text|
      text.concat "For want of a #{wanted_item} the #{lost_item} was lost.\n"
    end
  end

  def ending
    "And all for the want of a #{final_object}"
  end

  def final_object
    "#{@qualifier} #{@items.first}.".strip
  end
end
