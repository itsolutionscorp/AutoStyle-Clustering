class Hamming
  
  def compute s1, s2
    s1.each_char.with_index.inject(0) { |ham, (char, idx)|
      ham += 1 if s2[idx] && char != s2[idx]
      ham
    }
  end
end
