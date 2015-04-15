class Complement

  DNA_COMPLEMENT = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  def self.of_dna(dna)
    result = ''
    dna.each_char do |nucleotide|
      result << DNA_COMPLEMENT[nucleotide]
    end
    result
  end

  def self.of_rna(rna)
    result = ''
    rna.each_char do |nucleotide|
      result << DNA_COMPLEMENT.invert[nucleotide]
    end
    result
  end
end
