class Numeric
  LETTERS = {
    'M'   => 1000,
    'CM'  => 900,
    'D'   => 500,
    'CD'  => 400,
    'C'   => 100,
    'XC'  => 90,
    'L'   => 50,
    'XL'  => 40,
    'X'   => 10,
    'IX'  => 9,
    'V'   => 5,
    'IV'  => 4,
    'I'   => 1
  }

  def to_roman
    rest = self
    roman = ''

    until rest.zero? do
      LETTERS.each do |letter,value|
        if value <= rest
          rest -= value
          roman += letter
          break
        end
      end
    end

    roman
  end
end
