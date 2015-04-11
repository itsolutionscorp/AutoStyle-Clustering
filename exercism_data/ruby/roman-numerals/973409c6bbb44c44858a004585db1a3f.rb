class Fixnum
  ARABIC_TO_ROMAN = { 1_000 => "M", 900=> "CM", 500 => "D", 400 => "CD",
                      100 => "C", 90 => "XC", 50 => "L", 40 => "XL", 10 => "X",
                      9 => "IX", 5 => "V", 4 => "IV", 1 => "I" }
  def to_roman
    arabic = self

    ARABIC_TO_ROMAN.keys.each_with_object('') do |value, roman|
      arabic, roman = partly_romanize(value, arabic, roman)
    end
  end

  private

  def partly_romanize(value, arabic, roman)
    times_divisible = arabic / value

    times_divisible.times do
      roman << ARABIC_TO_ROMAN[value]
    end

    new_arabic = arabic - (times_divisible * value)

    [new_arabic, roman]
  end
end
