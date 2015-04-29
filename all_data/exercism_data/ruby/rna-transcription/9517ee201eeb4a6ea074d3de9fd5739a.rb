class Complement
  @@dna_complement = { 'A' => 'U',
                     'T' => 'A',
                     'C' => 'G',
                     'G' => 'C' }

  @@rna_complement = { 'A' => 'T',
                     'U' => 'A',
                     'C' => 'G',
                     'G' => 'C' }

  def self.of_dna seq
    return seq.chars.map {|c| @@dna_complement[c] }.join
  end

  def self.of_rna seq
    return seq.chars.map {|c| @@rna_complement[c] }.join
  end
end
