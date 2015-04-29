class Grains
  def square(n)
    (1..n).inject { |m| m*2 }
  end

  def total
    square(65) - 1
  end
end
