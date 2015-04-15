class Grains

  def square(squares)
    2**(squares-1)
  end

  def total
    (1..64).inject { |total, i| total += square(i) }
  end
end
