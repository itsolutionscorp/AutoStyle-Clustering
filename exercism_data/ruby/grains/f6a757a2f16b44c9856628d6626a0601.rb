class Grains
  def square(number)
    2 ** (number - 1)
  end

  def total
    (1..64).inject { |total, s| total += square(s) }
  end
end
