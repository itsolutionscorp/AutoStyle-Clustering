class Raindrops
  def convert(a)
    Droplet.new(a).to_s
  end
end

class Droplet
  def initialize(d)
    @d = d
  end

  def to_s
    e = []
    e << "Pling" if divisible_by?(3)
    e << "Plang" if divisible_by?(5)
    e << "Plong" if divisible_by?(7)

    e.empty? ? @d.to_s : e.join
  end

  def divisible_by?(n)
    @d % n == 0
  end
end
