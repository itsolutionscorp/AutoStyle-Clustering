class Hamming

  def self.compute(sequence, mutation)
    sequence = sequence.split('')
    mutation = mutation.split('')
    sequence.zip(mutation).count do |string_a,string_b| 
      self.substitution_check(string_a,string_b)
    end
  end

  def self.substitution_check(string_a,string_b)
    unless string_a == nil || string_b == nil
        string_a != string_b
      end
  end

end
