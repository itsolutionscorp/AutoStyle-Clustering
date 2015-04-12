def compute sampleA, sampleB
    minSampleLength = [sampleA.length, sampleB.length].min
    minSampleLength.times.count do |i|
      sampleA[i] != sampleB[i]
    end
  end