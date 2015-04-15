class Grains

  def square(a)
    2 ** (a - 1)
  end

  def total
    (1..64).reduce(0) {|r, n| r+= square(n); r}
  end
end
