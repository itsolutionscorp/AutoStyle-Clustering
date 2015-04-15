class Fixnum
  def to_roman
    map = {
      'M' => 1000, 'CM' => 900, 'D' => 500, 'CD' => 400,
      'C' => 100,  'XC' => 90,  'L' => 50,  'XL' => 40,
      'X' => 10,   'IX' => 9,   'V' => 5,   'IV' => 4,
      'I' => 1
    }

    _self = self

    map.reduce('') do |string, (symbol, value)|
      while _self >= value
        string << symbol
        _self -= value
      end
      string
    end
  end
end
