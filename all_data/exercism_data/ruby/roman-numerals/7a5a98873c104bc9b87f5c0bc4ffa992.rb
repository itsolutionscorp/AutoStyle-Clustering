class Integer
  ROMAN_NUMERALS = {
      1000 => 'M',
      900 => 'CM',
      500 => 'D',
      400 => 'CD',
      100 => 'C',
      90 => 'XC',
      50 => 'L',
      40 => 'XL',
      10 => 'X',
      9 => 'IX',
      5 => 'V',
      4 => 'IV',
      1 => 'I'
  }

  # def to_roman
  #   ROMAN_NUMERALS.each do |key, value|
  #     if self <= 0
  #       return ""
  #     elsif key <= self
  #       return value + (self - key).to_roman
  #     end
  #   end
  # end

  def to_roman
    original = self
    result = ""

    ROMAN_NUMERALS.each do |key, value|
     while original >= key
        result += value
        original = (original - key)
      end
    end

    result
  end

end
