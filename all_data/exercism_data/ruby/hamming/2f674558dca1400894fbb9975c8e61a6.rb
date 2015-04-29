class Hamming

  # Calculates the Hamming distance between strings s & t (representing two DNA strands)
  def self.compute(s, t)
    score = 0
    # iterate through character array of s
    s.each_char.with_index do |c, i|
      # compare to equivalent position in t; if one is longer than the other, ignore extra characters
      score += 1 if t[i] && c != t[i]
    end
    score
  end

end
