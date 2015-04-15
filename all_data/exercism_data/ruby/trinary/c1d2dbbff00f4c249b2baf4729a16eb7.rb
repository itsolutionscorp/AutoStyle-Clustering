class Trinary
  attr_reader :text
  def initialize(text)
    @text = text.gsub(/[^0-2]/,'')
  end
 
  def to_decimal
    dec = 0
    text.chars.reverse.each_with_index do |ch, i|
      dec += ch.to_i*(3**i)
    end
    dec
  end
end
