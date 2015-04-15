class Hamming
  def self.compute(first, second)
    diff = 0
    (0..first.length - 1).each do |idx|
      diff += 1 if first[idx] != second[idx]
    end
    diff
  end
end
