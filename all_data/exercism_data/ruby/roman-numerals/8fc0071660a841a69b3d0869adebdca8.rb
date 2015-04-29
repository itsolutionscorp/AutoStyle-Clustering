class Fixnum

  def to_roman
    ArabicToRoman.for(self)
  end

end


module ArabicToRoman

SINGLE_DIGITS = ["",  "I", "II", "III",
                "IV", "V", "VI", "VII",
                "VIII","IX"]

TENS_DIGITS = SINGLE_DIGITS.map do |value|
                value.gsub(/[XVI]/,
                  { "X" => "C",
                    "V" => "L",
                    "I" => "X"})
                end

HUNDREDS_DIGITS = SINGLE_DIGITS.map do |value|
                    value.gsub(/[XVI]/,
                      { "X" => "M",
                        "V" => "D",
                        "I" => "C"})
                    end

THOUSANDS_DIGITS = [ "", "M", "MM", "MMM" ]


  def self.for(arabic_num)
    converters = [SINGLE_DIGITS,TENS_DIGITS,HUNDREDS_DIGITS , THOUSANDS_DIGITS]

    3.downto(0).reduce("") do |roman, x|
       remainder = arabic_num % 10**(x+1)
       roman += converters[x][remainder/10**x]
    end
  end
end
