class Grains
  def square(n)
    grains(n)
  end

  def total
    (1..64).map { |n| grains(n) }
           .inject(:+)
           .to_i
  end

  private

  def grains(n)
    2**(n - 1)
  end
end
