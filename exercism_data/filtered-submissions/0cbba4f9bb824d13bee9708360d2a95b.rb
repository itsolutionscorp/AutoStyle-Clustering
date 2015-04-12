def compute sampleA, sampleB
    common_length = [sampleA.length, sampleB.length].min
    common_length.times.count do |i|
      sampleA[i] != sampleB[i]
    end
  end