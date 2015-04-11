class Grains

  def square(i)
    grains = []
    x = 1
    i.times do |square|
      grains << x
      x = x*2
    end
    grains.last
  end

  def total
    grains = []
    total = 0
    x = 1
    64.times do |square|
      grains << x
      x = x*2
    end
    grains.each do |x|
      total += x
    end
    total
  end
end
