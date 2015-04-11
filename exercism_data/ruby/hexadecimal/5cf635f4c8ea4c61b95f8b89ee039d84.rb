class Hexadecimal
  attr_reader :sequence
  def initialize hex_str
    @sequence = hex_str.scan(/[0-9a-f]/)
    @sequence = ["0"] unless hex_str.scan(/[^0-9a-f]/).empty?
  end
  
  def to_decimal
    sequence.reverse.each_with_index.inject(0) do |ret,(element,index)|
      ret + ((element.ord&15)+(element.ord>>6)*9)*(16**index)
    end
  end
end
