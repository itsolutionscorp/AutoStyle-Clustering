=begin
  File: grains.rb
  Author: sherinom
=end

class Grains

  def square(number)
    2**(number - 1)
  end

  def total
    total_grains = 0
    (1..64).each do |x|
      total_grains += square x
    end
    total_grains
  end

end
