class Trinary
  
  def initialize(s)
    @the_number = s
  end
  
  def to_decimal    
    decimal = 0
    @the_number.split("").reverse.each_with_index do |n,i|
      decimal += n.to_i * 3**i
    end
    decimal  
  end  
  
end  
