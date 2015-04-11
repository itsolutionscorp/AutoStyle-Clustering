class Hamming
  def self.compute(strandA, strandB)
    differences = 0
    strandA.chars.each_with_index do |base, index|
      next if base == strandB[index]
        differences += 1
    end
    differences
  end
end
