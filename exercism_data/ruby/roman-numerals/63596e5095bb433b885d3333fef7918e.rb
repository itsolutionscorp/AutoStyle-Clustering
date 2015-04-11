class Fixnum
  ROMAN_NUMERALS = {
        :'1'    => 'I',
        :'5'    => 'V',
        :'10'   => 'X',
        :'50'   => 'L',
        :'100'  => 'C',
        :'500'  => 'D',
        :'1000' => 'M'
  }

  def digits
    self.to_s.chars.map(&:to_i)
  end

  def to_roman
    digits = self.digits
    roman = digits.each_with_index.map do |digit, index|
      power = (10 ** (digits.count - index)) / 10
      if digit == 4 || digit == 9
        ROMAN_NUMERALS[(power).to_s.to_sym] + ROMAN_NUMERALS[((digit == 4 ? 5 : 10) * power).to_s.to_sym]
      elsif digit >= 5
        ROMAN_NUMERALS[(5 * power).to_s.to_sym] + (ROMAN_NUMERALS[power.to_s.to_sym] * (digit - 5))
      else
        ROMAN_NUMERALS[power.to_s.to_sym] * digit
      end
    end
    roman.join
  end
end
