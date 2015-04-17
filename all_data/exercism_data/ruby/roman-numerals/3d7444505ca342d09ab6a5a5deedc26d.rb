class Fixnum
  NUMERALS = {
        1000 => "M", 
        900 =>  "CM", 
        500 =>  "D",
        400 =>  "CD",
        100 =>  "C",
        90 =>   "XC",
        50 =>   "L",
        40 =>   "XL", 
        10 =>   "X", 
        9 =>    "IX", 
        5 =>    "V", 
        4 =>    "IV", 
        1  =>   "I" }

  def to_roman(value = self.to_i)
    NUMERALS.each_with_object("") do|(num, letter), retval| 
      while value - num >= 0
        retval << letter
        value -= num
      end 
    end
  end
end