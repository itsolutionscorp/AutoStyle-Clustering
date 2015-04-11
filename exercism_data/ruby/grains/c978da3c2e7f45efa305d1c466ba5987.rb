class Grains
  def square position
    2**(position - 1)
  end

  def total
    (1..64).to_a.inject { |sum, i | sum + square(i) }
  end
end
