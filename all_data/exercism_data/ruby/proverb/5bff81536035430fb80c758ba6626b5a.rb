class Proverb

  def initialize(*items)
    if items.last.is_a? Hash
      @items = items[0...(items.length-1)]
      @qualifier = items.last[:qualifier]
    else
      @items = items
      @qualifier = nil
    end
  end

  def to_s
    s = ""
    prev = ""
    @items.each_with_index do |item, index|
      s += "For want of a #{prev} the #{item} was lost.\n" unless index.zero?
      prev = item
    end
    if @qualifier
      s += "And all for the want of a #{@qualifier} #{@items.first}."
    else
      s += "And all for the want of a #{@items.first}."
    end
  end
end
