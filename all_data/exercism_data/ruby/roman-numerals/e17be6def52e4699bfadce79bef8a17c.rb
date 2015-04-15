class Fixnum
  # Calculation of MIN and MAX is taken from http://stackoverflow.com/a/535751/826797
  N_BYTES = [42].pack('i').size
  N_BITS  = N_BYTES * 8
  MAX     = 2 ** (N_BITS - 2) - 1
  MIN     = -MAX - 1
  
  def to_roman
    case self
    when 1000..MAX then "M"  + (self - 1000).to_roman
    when  900..999 then "CM" + (self -  900).to_roman
    when  500..899 then "D"  + (self -  500).to_roman
    when  400..499 then "CD" + (self -  400).to_roman
    when  100..399 then "C"  + (self -  100).to_roman
    when   90.. 99 then "XC" + (self -   90).to_roman
    when   50.. 89 then "L"  + (self -   50).to_roman
    when   40.. 49 then "XL" + (self -   40).to_roman
    when   10.. 39 then "X"  + (self -   10).to_roman
    when    9      then "IX"
    when    5..  8 then "V" + (self -     5).to_roman
    when    4      then "IV"
    else "I" * self
    end
  end
end
