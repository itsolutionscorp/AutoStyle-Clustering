def compute(strand1, strand2)
    @strand1array = strand1.split("")
    @strand2array = strand2.split("")
    iterator = 0
    count = 0

    @strand1array.zip(@strand2array).each do |a, b|
      break if iterator == @strand1array.length || iterator == @strand2array.length
      if a != b
        count = count + 1
      end
      iterator = iterator + 1
    end
    return count
  end