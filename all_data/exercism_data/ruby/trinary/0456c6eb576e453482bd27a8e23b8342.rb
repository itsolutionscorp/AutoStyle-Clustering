class Trinary
  attr_reader :text
  def initialize(text)
    @text = text.gsub(/[^a-z0-2]/,'') ;p @text
  end
 
  def to_decimal
    dec = 0
    trinary_array.each_with_index do |ch, i|
      dec += ch.to_i*(3**i)
    end
    dec
  end

  def trinary_array
    text.chars.reverse
  end
end
