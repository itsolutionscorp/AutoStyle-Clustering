class Hamming

  def self.compute(sequence1, sequence2)
    sequence1 = sequence1.split('')
    sequence2 = sequence2.split('')
    sequence1.zip(sequence2).count do |string_a, string_b| 
      self.substitution?(string_a, string_b)
    end
  end

  def self.substitution?(string_a, string_b)
    unless string_a == nil || string_b == nil
      string_a != string_b
    end
  end

end
