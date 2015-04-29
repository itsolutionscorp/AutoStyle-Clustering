class Complement
  def self.dna_complement
    {
      C: 'G',
      G: 'C',
      T: 'A',
      A: 'U'
    }
  end

  def self.rna_complement
    {
      C: 'G',
      G: 'C',
      U: 'A',
      A: 'T'
    }
  end

  def self.of_dna strand
    strand = strand.split('')
    strand.map{|s| dna_complement[s.to_sym]}.join('')
  end

  def self.of_rna strand
    strand = strand.split('')
    strand.map{|s| rna_complement[s.to_sym]}.join('')
  end
  
end
