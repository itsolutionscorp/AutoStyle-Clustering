class Grains
  def square(n)
    2 ** (n - 1)
  end

  def total
    sum = 0

    64.times do |i|
      sum += square(i + 1)
    end

    sum
  end
end
