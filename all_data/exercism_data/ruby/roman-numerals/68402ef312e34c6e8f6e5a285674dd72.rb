class Fixnum
  def to_roman
    Roman.new(self).to_s
  end 
end

class Roman
  MAX_ROMAN = 3000

  def initialize(value)
    if value <= 0 || value > MAX_ROMAN
      fail "Roman values must be > 0 and <= #{MAX_ROMAN}"
    end
    @value = value
  end

  FACTORS = { 'M' => 1000, 'CM' => 900, 'D' => 500, 'CD' => 400,
              'C' => 100, 'XC' => 90, 'L' => 50, 'XL' => 40, 
              'X' => 10, 'IX' => 9, 'V' => 5, 'IV' => 4, 
              'I' => 1 }

  def to_s
    value = @value
    roman_numeral = ''
    FACTORS.each do |roman, arabic|
      count, value = value.divmod(arabic)
      roman_numeral << (roman * count)
    end
    roman_numeral
  end

end
