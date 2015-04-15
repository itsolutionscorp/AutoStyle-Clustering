class Fixnum
  def to_roman
    num_to_roman = {
      'M'  => 1000,
      'CM' => 900,
      'D'  => 500,
      'CD' => 400,
      'C'  => 100,
      'XC' => 90,
      'L'  => 50,
      'XL' => 40,
      'X'  => 10,
      'IX' => 9,
      'V'  => 5,
      'IV' => 4,
      'I'  => 1
    }

    num = self
    string = ''

    num_to_roman.each do |symbol, value|
      while num >= value
        string << symbol
        num -= value
      end
    end
    string
  end
end
