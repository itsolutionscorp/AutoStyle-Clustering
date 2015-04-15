def compute(str1, str2)
    nuclearDistance = 0
    # counter = 0
    strandLength = str1.length - 1

    (0..strandLength).each do | char |
      difference = str1[char] <=> str2[char]

      nuclearDistance += difference.abs
    end
    return nuclearDistance
  end