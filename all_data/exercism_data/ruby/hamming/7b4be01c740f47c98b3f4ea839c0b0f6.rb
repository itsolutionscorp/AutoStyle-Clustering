class Hamming
  def self.compute(a, b)
    Hamming.arrayize(a, b)
    count = 0
    @a_ray.length.times do |i|
      count += Hamming.compare(@a_ray[i], @b_ray[i])
    end
    count
  end

  def self.arrayize(a, b)
    @a_ray = a.split("")
    @b_ray = b.split("")    
  end

  def self.compare(a, b)
    if a == b
      0
    else
      1
    end
  end
end
