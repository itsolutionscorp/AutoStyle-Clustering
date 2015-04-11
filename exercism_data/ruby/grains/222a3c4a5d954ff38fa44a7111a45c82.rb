class Grains
  def square(n)
    wheat_on_square = 0.5
    (n).times do
      wheat_on_square *= 2
    end
    wheat_on_square.to_i
  end

  def total
    (1..64).reduce(0) {|sum, n| sum += square(n)}
  end
end
