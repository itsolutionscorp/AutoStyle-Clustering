class Complement
  PAIRS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.find_complement(input_strand, type)
    output_strand = []
    input_strand.chars.each do |base|
      output_strand << PAIRS[base] if type == 'dna'
      output_strand << PAIRS.key(base) if type == 'rna'
    end
    output_strand.join('')
  end

  def self.of_dna(dna_strand)
    self.find_complement(dna_strand,'dna')
  end

  def self.of_rna(rna_strand)
    self.find_complement(rna_strand,'rna')
  end
end
