class Grains
  def square(number)
    return number if number < 3
    (3..number).inject(2) { |sum| sum * 2 }
  end

  def total(squares = 64)
    (1..squares).inject(0) do |sum, number|
      sum + square(number)
    end
  end
end
