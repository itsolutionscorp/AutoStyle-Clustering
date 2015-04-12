module Hamming

  module_function

  # Given two strings, count the number of characters
  # that are different
  # params:
  #   String strand_1
  #   String strand_2
  # returns Integer
  def compute(strand_1, strand_2)

    # Convert both strings to arrays, noting which one is shorter
    # (If they're both the same length, it doesn't matter)
    if strand_1.length < strand_2.length
      short = strand_1.chars
      long = strand_2.chars
    else
      short = strand_2.chars
      long = strand_1.chars
    end
    
    count = 0

    # Add up the number of differing characters, stopping when
    # the shorter string ends
    short.each_with_index do |c,i|
      count += 1 unless c == long[i]
    end

    count
  end
end
