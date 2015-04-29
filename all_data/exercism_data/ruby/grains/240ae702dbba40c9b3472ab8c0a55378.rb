class Grains
  def square(number)
    (1..number).to_a.reduce{|m, n| m *= 2}
  end
  def total
    total = (1..64).inject(0) { |total, number| total + square(number)}.to_i
  end
end
