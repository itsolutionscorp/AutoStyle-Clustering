def compute(standA, standB)
    sampleA = standA.chars
    sampleB = standB.chars
    count = 0
    if sampleA.length == sampleB.length
      sampleA.each_with_index do |letter, index|
        if letter != sampleB[index] && sampleB[index] != nil
          count += 1
        end
    end
    count
    else 
      puts 'stings are not of equal length'
    end
  end