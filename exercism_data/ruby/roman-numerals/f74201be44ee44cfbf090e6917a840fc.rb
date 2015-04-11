class Fixnum
  VALUES = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
  ROMAN  = %w(M CM D CD C XC L XL X IX V IV I)
  N2R = Hash[VALUES.zip(ROMAN)]

  def to_roman
    num = self
    numerals = ''
    while num > 0 do
      v = VALUES[VALUES.index {|i| i<=num }]
      times = num/v
      numerals << N2R[v]*times
      num -= v*times
    end
    numerals
  end

end
