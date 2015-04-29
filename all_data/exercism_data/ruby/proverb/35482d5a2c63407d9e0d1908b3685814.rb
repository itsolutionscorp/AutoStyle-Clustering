class Proverb
  def initialize(*items, qualifier: nil)
    @items = items
    @qualifier = qualifier
  end

  def to_s
    string = []
    @items.each_cons(2) do |items|
      string << "For want of a #{items[0]} the #{items[1]} was lost."
    end
    if @qualifier
      qualified = "#{@qualifier} #{@items[0]}"
    else
      qualified = "#{@items[0]}"
    end
    string << "And all for the want of a #{qualified}."
    string.join("\n")
  end
end
