class Hamming

  # Calculates the Hamming distance between strings s & t (representing two DNA strands)
  def self.compute(s, t)
    # set starting score and break t into character array
    score = 0
    # iterate through character array of s and compare to character at same index in t
    s.each_char.with_index do |c, i|
      # compare to equivalent position in s; if one is longer than the other, ignore extra characters
      score += 1 if t[i] && c != t[i]
    end
    score
  end

end
