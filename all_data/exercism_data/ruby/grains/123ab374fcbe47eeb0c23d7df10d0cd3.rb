class Grains
  def square(number)
    2 ** (number - 1)
  end
  
  def total(upto=64)
    (1..upto).inject{|sum, i| sum + square(i)}
  end
end
