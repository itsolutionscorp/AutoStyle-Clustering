class Grains

  def square(num)
    1 << num-1
  end

  def total
    total = 0
    (1..64).inject { |total, num| total + square(num) }
#    (1..64).each do |num|
 #     total += square(num)     
 #   end
 #   total
  end

end