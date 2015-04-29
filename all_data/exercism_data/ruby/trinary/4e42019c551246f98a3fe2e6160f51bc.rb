class Trinary
  
  def initialize trinary
    @trinary = trinary.to_i
  end

  def to_decimal
    decimal = 0
    index = 0
    @trinary.to_s.reverse.each_char do |digit|
      decimal += 3**index * digit.to_i 
      index += 1
    end
    decimal
  end
end
