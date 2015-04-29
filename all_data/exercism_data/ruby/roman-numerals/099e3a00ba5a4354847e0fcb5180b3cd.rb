class Integer < Numeric
  def to_roman
    n = self
    m = {
      1000 => 'M', 900 => 'CM', 500 => 'D', 400 => 'CD', 100 => 'C',
      90 => 'XC', 50 => 'L', 40 => 'XL', 10 => 'X', 9 => 'IX', 5 => 'V',
      4 => 'IV', 1 => 'I'
    }

    m.map { |k, v|
      if n / k > 0
        z = n / k
        n -= k * z
        v * z
      end
    }.join('')
    
  end
end
