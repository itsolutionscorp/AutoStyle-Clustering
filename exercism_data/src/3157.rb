def compute(strand1, strand2)
    # return count of differences
    [strand1.chars, strand2.chars].transpose.select { |chars1, chars2| chars1 != chars2}.count
  end