class Hamming
  def self.compute(one, two)
    limit = [one.size, two.size].min
    (0...limit).select { |i| one[i] != two[i] }.size
  end
end
