class Hamming
  def compute(strandA, strandB)
    hammingCount = 0

    strandA.chars.each_with_index do |char, i|
      hammingCount += 1 if char != strandB[i]
    end

    hammingCount
  end
end
