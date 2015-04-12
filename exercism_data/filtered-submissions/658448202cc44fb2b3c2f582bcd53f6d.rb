class Hamming
  def compute sampleA, sampleB
    minSampleLength = [sampleA.length, sampleB.length].min
    result = 0
    minSampleLength.times do |i|
      result += 1 unless sampleA[i] == sampleB[i]
    end
    return result
  end
end
