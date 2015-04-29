class Grains
  def square(num)
    1 << num-1
  end

  def total
    (1..64).reduce{ |total, n| total + (1 << n-1) }
  end
end
