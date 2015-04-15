class Grains

  def square(number)
    2 ** (number-1)
  end

  def total
    (1..64).inject do |total, value|
      total += 2 ** (value-1)
    end
  end
end
