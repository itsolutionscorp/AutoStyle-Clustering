class Hamming
  def self.compute(s1, s2)
    s1_chars = s1.chars
    s2_chars = s2.chars
    hamming = 0

    # lenth of chars to compare, shortest length of the two strands
    len = s1_chars.length > s2_chars.length ? s2_chars.length : s1_chars.length
    len -= 1 # convert len from 1 base to 0 base

    for i in 0..len
      #puts "Comparing #{s1_chars[i]} and #{s1_chars[i]}"
      if s1_chars[i] != s2_chars[i]
        hamming += 1
      end
    end

    hamming
  end
end
