class Grains

  def square(num)
    grain = 1
    (num - 1).times { grain *= 2 }
    grain
  end

  def total
    1.upto(64).reduce(0) { |grains, grain| grains += square(grain) }
  end

end
