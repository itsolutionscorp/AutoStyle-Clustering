class Fixnum

  arabic = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
  roman  = %w[M CM D CD C XC L XL X IX V IV I]
  DIGITS = arabic.zip roman

  def to_roman
    result = ""
    num = self

    DIGITS.each do |limit, glyph|
      while num >= limit
        result << glyph
        num -= limit
      end
    end
    result
  end
  
end
