class Octal
  def initialize octalnumber
    @oct = octalnumber.gsub(/^0+/,"") # String#gsub strips leading zeroes so that if there are leading zeroes in the input (as in the last test), they won't end up on the end of the number when it's reversed
  end
 
  def to_decimal
    dec = 0
    @oct.to_s.reverse.split("").each_with_index do |n, index|
      if (n.to_i < 8) && (n == n.to_i.to_s)
        dec += n.to_i * (8 ** index)
      else
        dec = 0
        break
      end
    end
    dec
  end
end

# Got the conversion formula from here: http://en.wikipedia.org/wiki/Octal#Octal_to_decimal_conversion
