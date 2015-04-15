class Grains
  def square(index)
    2**(index - 1)
  end

  def total
    (1..64).reduce {|base, curr| base + square(curr)}
  end
end
