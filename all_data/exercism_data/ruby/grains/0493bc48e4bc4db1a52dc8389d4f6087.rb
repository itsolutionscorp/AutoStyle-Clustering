class Grains

  def square(number)
    (1..number).inject {|sum| sum * 2}
  end

  def total
    (1..64).inject {|sum, i| sum + square(i) }
  end
  
end
