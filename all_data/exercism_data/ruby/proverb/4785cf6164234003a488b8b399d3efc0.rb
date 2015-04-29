class Proverb
  def initialize(*args, qualifier: '')
    @items = args
    @qualifier = qualifier
  end

  def to_s
    verses = @items.each_cons(2).map do |wanted, lost|
      "For want of a #{wanted} the #{lost} was lost."
    end

    verses << "And all for the want of a #{qualified_first_item}."

    verses.join("\n")
  end

  private

  def qualified_first_item
    "#{@qualifier} #{@items.first}".strip
  end
end
