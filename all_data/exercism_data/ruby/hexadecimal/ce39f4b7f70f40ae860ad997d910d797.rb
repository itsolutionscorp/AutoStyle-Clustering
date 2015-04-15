class Hexadecimal
  attr_reader :sequence
  def initialize hex_str
    @sequence = hex_str.scan(/[0-9a-f]/)
    @sequence = ["0"] unless hex_str.scan(/[^0-9a-f]/).empty?
  end
  
  def to_decimal
    sequence.reverse.each_with_index.inject(0) do |ret,pair|
      element, index = pair
      ret + element.hex * (16 ** index)
      #ret + (element&15)+(element>>6)*9
    end
  end
end
