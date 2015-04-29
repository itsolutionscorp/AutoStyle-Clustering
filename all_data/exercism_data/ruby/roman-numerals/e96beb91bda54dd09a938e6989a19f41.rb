class Fixnum

  def to_roman
    ArabicToRoman.for(self)
  end

end


module ArabicToRoman

SINGLE_DIGITS = { 0 => "", 1 => "I", 2 => "II", 3 => "III",
                  4 => "IV", 5 => "V", 6 => "VI",
                  7 => "VII", 8 => "VIII", 9 => "IX"}

TENS_DIGITS = SINGLE_DIGITS.map do |key, value|
                value.gsub(/[XVI]/, { "X" => "C", "V" => "L", "I" => "X"})
              end

HUNDREDS_DIGITS = SINGLE_DIGITS.map do |key, value|
                    value.gsub(/[XVI]/, { "X" => "M", "V" => "D", "I" => "C"})
                  end

THOUSANDS_DIGITS = { 0 => "", 1 => "M", 2 => "MM", 3 => "MMM" }


  def self.for(arabic_num)
    converters = [SINGLE_DIGITS,TENS_DIGITS,HUNDREDS_DIGITS , THOUSANDS_DIGITS]

    3.downto(0).reduce("") do |roman, x|
       remainder = arabic_num % 10**(x+1)
       roman += converters[x][remainder/10**x]
    end
  end
end
