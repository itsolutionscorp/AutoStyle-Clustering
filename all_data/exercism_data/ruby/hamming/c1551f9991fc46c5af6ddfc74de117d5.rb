class Hamming
  def self.compute strand1, strand2
    self.ensure_valid_lengths strand1, strand2
    hamming_count = 0
    strand1.each_char.each_with_index do |base, index|
      hamming_count += 1 if self.mutated?(base, strand2[index])
    end
    hamming_count
  end

  def self.ensure_valid_lengths strand1, strand2
    if strand1.length != strand2.length
      raise ArgumentError , "strand lengths must be equal"
    end
  end

  def self.mutated? base1, base2
    base1.downcase != base2.downcase
  end
end
