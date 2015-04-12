class Hamming

  # Calculates the Hamming distance between strings s & t (representing two DNA strands)
  def compute(s, t)
    # set starting score and break t into character array
    score = 0
    t_array = t.chars.to_a # also t.split('') or t.scan('.'), similarly for s
    # iterate through character array of s
    s.each_char.with_index do |c, i|
      # compare to equivalent position in s; if one is longer than the other, ignore extra characters
      score += 1 if t_array[i] && c != t_array[i]
    end
    score
  end

end
