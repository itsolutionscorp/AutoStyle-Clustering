class Hamming

  def self.compute(strand_one, strand_two)
    strand_one = strand_one.split('')
    strand_two = strand_two.split('')

    strand_one.zip(strand_two).count do |string_a, string_b|
      self.substitution?(string_a, string_b)
    end
  end

  def self.substitution?(string_a, string_b)
    unless string_a == nil || string_b == nil
      string_a != string_b
    end
  end

end
