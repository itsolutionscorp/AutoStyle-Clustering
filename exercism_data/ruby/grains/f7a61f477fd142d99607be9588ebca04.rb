class Grains
  
  attr_accessor :num,:square_num
  FIRST_SQUARE_VAL = 1
  
  def square(num)
     square_num = num
    if square_num <= 64
      grains_count_on_square = FIRST_SQUARE_VAL * (2**(square_num-1))    
      grains_count_on_square
    else
      p "Exceeded chess board range"
    end
  end
 
   
  
  def total
    numerator = (1-(2**64)).abs
    denominator = (1-2).abs
    square_tot = FIRST_SQUARE_VAL * (numerator/denominator)
    square_tot
  end
end

#p "Enter anumber"
#num = gets.chomp!()

#g = Grains.new
#sum =g.square(num.to_i)
#g.total
