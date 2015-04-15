class Hamming
  def self.compute(one, two)
    one.length.times.count { |i| one[i] != two[i] }
  end
end
