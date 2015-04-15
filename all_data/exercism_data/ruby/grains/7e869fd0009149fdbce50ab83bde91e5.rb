class Grains
  
  def square(num)
    (1..num).inject { |sum| sum * 2 } 
  end

  def total
    square(65) - 1
  end

end
