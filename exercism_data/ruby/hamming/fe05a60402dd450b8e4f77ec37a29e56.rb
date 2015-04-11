class Hamming

  # Calulates the Hamming distance between strings s & t (representing two DNA strands)
  def self.compute(s, t)
    score = 0
    t_array = t.chars.to_a # also t.split('') or t.scan('.'), similarly for s
    s.each_char.with_index do |c, i|
      score += 1 if t_array[i] && c != t_array[i]
    end
    score
  end

  puts self.compute('AG','CT')

end
