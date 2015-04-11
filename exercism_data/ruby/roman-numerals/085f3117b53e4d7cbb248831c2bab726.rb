class Fixnum

  def to_roman
    result = ""
    n = self
    numbers = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
    roman  = %w[M CM D CD C XC L XL X IX V IV I]
    numbers2roman = numbers.zip roman
    numbers2roman.each do |num, roman|
      unless n < num
        result << roman
        n -= num
      end
    end
    result
  end
end
