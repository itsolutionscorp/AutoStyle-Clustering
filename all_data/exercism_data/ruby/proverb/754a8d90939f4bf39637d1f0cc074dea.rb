class Proverb
  def initialize(*args)
    @options = args.last.is_a?(Hash) ? args.pop : {}
    @items = args
  end

  def to_s
    verses = item_pairs.map do |wanted, lost|
      "For want of a #{wanted} the #{lost} was lost."
    end

    verses << "And all for the want of a #{final_wanted_item}."

    verses.join("\n")
  end

  private

  def item_pairs
    items_without_first = @items[1..-1]
    items_without_last = @items[0..-2]

    items_without_last.zip(items_without_first)
  end

  def final_wanted_item
    if @options[:qualifier]
      "#{@options[:qualifier]} #{@items.first}"
    else
      @items.first
    end
  end
end
