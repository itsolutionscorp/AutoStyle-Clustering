class RomanNumerals
  @romans = {
    1     => "I"  ,
    4     => "IV" ,
    5     => "V"  ,
    9     => "IX" ,
    10    => "X"  ,
    40    => "XL" ,
    49    => "IL" ,
    50    => "L"  ,
    90    => "XC" ,
    99    => "IC" ,
    100   => "C"  ,
    400   => "CD" ,
    490   => "XD" ,
    499   => "ID" ,
    500   => "D"  , 
    900   => "CM" ,
    990   => "XM" ,
    999   => "IM" ,
    1000  => "M"
  }

  def self.to_roman num
    roman = ""
    while num > 0 do
      component = @romans.largest_component num
      # puts "#{component} - #{@romans[component]}"
      roman << @romans[component]
      num -= component
    end
    return roman
  end
end

Hash.class_eval do
  def largest_component num
    self.select {|i| i <= num}.keys.last
  end
end

Fixnum.class_eval do
  def to_roman
    RomanNumerals.to_roman self
  end
end
