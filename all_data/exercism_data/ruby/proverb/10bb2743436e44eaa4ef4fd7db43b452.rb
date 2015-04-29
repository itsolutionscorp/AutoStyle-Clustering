class Proverb
  def initialize(*items)
    @items = items
    if @items.last.is_a? Hash
      @qualifier = @items.pop[:qualifier]
    else
      @qualifier = nil
    end
  end

  def to_s
    text_array = []
    (@items.length - 1).times do |index|
      text_array << "For want of a #{@items[index]} the #{@items[index + 1]} was lost.\n"
    end
    @qualifier == nil ? q_text = "" : q_text = @qualifier + " "
    text_array << "And all for the want of a #{q_text}#{@items[0]}."
    text_array.join("")
  end
end
