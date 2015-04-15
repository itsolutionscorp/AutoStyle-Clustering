class Hamming
  def self.compute(sample1, sample2)
    count = 0
    sample1.chars.each_with_index.count do |sample, n|
      count += 1 if sample != sample2[n]
    end
    return count
  end
end
