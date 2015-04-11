class Proverb

  def initialize(*items)
    @qualifier = items[-1][:qualifier] if items[-1].class == Hash
    @items = @qualifier ? items[0..-2] : items
  end

  def to_s
    stanza = ""
    @items.each_cons(2) do |pair|
      stanza += "For want of a #{pair[0]} the #{pair[1]} was lost.\n"
    end
    stanza += final_line
  end

  private

  def final_line
    if @qualifier
      "And all for the want of a #{@qualifier + ' ' + @items[0]}."
    else
      "And all for the want of a #{@items[0]}."
    end
  end

end
