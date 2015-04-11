class Proverb
  def initialize(*items, qualifier: nil)
    @items = items
    @qualifier = qualifier
  end

  def to_s
    text_array = []
    (@items.length - 1).times do |index|
      text_array << "For want of a #{@items[index]} the #{@items[index + 1]} was lost.\n"
    end
    q_text = @qualifier + " " if !@qualifier.nil?
    text_array << "And all for the want of a #{q_text}#{@items[0]}."
    text_array.join("")
  end
end
