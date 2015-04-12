class Hamming
  def compute(dna_a, dna_b)
    unless dna_a.is_a?(String) && dna_b.is_a?(String)
      raise ArgumentError "Arguments must both be strings."
    end

    test_len = [dna_a.length, dna_b.length].min

    (0..test_len - 1).count do |i|
      dna_a[i] != dna_b[i]
    end
  end
end
