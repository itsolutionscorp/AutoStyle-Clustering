module Roman
  def to_roman
    result = ""
    to_s.chars.reverse.each_with_index do |number, index|
      result.prepend RomanDigit.new(number,index).to_roman
    end
    result
  end

end

class RomanDigit
  MAPPINGS= { 0 => "", 1 => "I", 2 => "II", 3 => "III", 4 => "IV", 5 => "V", 6 => "VI", 7 => "VII", 8 => "VIII", 9 => "IX" }
  INDEX_CHARACTERS = { 0 => "IVX", 1 => "XLC", 2 => "CDM", 3 => "MAA" }

  def initialize digit, index
    @digit = digit.to_i
    @index = index.to_i
  end

  def to_roman
    MAPPINGS[@digit].tr "IVX", INDEX_CHARACTERS[@index]
  end

end

class Fixnum
  include Roman
end
