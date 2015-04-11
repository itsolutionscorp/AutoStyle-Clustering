class Hamming
  def self.compute(a, b)
    distance = 0
    longest(a, b).times do |idx|
      distance += 1 if a[idx] != b[idx]
    end
    distance
  end

  def self.longest(*args)
    args.map(&:length).max
  end
end
