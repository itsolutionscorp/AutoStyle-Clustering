class Grains

  def square grains
    first = 1
    (grains - 1).times {first *= 2}
    first
  end

  def total
    vals = []
    1.upto(64) do |grains|
      vals << square(grains)
    end
    vals.reduce(:+)
  end

end
