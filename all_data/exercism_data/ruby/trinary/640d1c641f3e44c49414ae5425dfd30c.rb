class Trinary
  attr_reader :text
  def initialize(text)
    @text = normalize text
  end
 
  def to_decimal
    dec = 0
    text.chars.reverse.each_with_index do |ch, i|
      dec += ch.to_i*(3**i)
    end
    dec
  end

  def normalize(text)
    text.match(/\A[0-2]*\z/) ? text : '0'
  end
end
