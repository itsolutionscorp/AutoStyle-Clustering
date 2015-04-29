class Hamming
  def self.compute sampleA, sampleB
    limit = [sampleA.length, sampleB.length].min
    result = 0
    (0...limit).each do |i|
      result += 1 unless sampleA[i] == sampleB[i]
    end
    return result
  end
end
