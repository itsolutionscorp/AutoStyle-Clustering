class Grains
  def square(number)
    2**(number-1)  
  end

  def total
    (1..64).map {|x| square(x)}.reduce :+
  end
end
