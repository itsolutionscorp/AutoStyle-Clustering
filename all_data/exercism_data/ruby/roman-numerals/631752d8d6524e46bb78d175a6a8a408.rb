class Fixnum

  def roman_values
    {1000=>"M", 900=>"CM", 500=>"D", 400=>"CD", 
      100=>"C",  90=>"XC",  50=>"L",  40=>"XL", 
       10=>"X",   9=>"IX",   5=>"V",   4=>"IV", 
        1=>"I"
    }
  end

  def to_roman
    roman_numerals = convert_to_roman_numerals(self).join
  end

  def convert_to_roman_numerals(number, numerals = [])
    roman_values.each_key do |key|
      loop do
        if number - key >= 0
          numerals << roman_values[key]
          number -= key
        else
          break
        end
      end
    end
    numerals
  end

end
