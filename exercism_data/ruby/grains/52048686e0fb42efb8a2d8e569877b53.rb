require 'pry'
class Grains

  def square(number)
    1 << (number - 1)
  end

  def total
    (1..64).inject(0) {|sum, number| sum += square(number)}
  end

end
