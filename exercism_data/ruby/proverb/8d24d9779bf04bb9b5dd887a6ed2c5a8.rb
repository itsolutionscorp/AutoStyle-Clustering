class Proverb
  def initialize(*items, qualifier: nil)
    @items = items
    @qualifier = qualifier
  end

  def to_s
    proverb = ""
    @items.each_index do |index|
      if @items[index + 1]
        proverb << "For want of a #{@items[index]} the #{@items[index + 1]} was lost.\n"
      else
        adjective = @qualifier + " " if @qualifier
        proverb << "And all for the want of a #{adjective}#{@items.first}."
      end
    end
    proverb
  end
end
