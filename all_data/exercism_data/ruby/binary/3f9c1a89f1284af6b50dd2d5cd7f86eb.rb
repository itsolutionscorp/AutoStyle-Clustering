class Binary
  def initialize(x)
    @x = x
  end

  def to_decimal
    if @x.each_char.any? { |x| x != "1" && x != "0"}
      full_number = 0 
    else
      power = 0
      full_number = 0 
      base = 2 

      @x.reverse.each_char do |x|
        spot_number = x.to_i * base.to_i**power
        full_number += spot_number
        power += 1
      end
    end
    full_number
  end
end
