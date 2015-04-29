class Grains

  def square(squares)
    2**(squares-1)
  end

  def total
    total = 0
    (1..64).each do |i|
      total += square(i)
    end
    return total
  end
end
