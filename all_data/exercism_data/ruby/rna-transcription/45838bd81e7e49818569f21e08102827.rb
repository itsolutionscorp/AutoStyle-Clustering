class Complement
  def self.of_rna(nucleotides)
    nucleotides.chars.map{ |nucleotide| complement_of(nucleotide, 'rna')}.join()
    
  end

  def self.of_dna(nucleotides)
    nucleotides.chars.map{ |nucleotide| complement_of(nucleotide, 'dna')}.join()
  end

  private
  def self.complement_of(nucleotide, type)
    dna = ['A','T','C','G']
    rna = ['U','A','G','C']
    begin
      complement = rna[dna.index(nucleotide)] if type == 'dna'
      complement = dna[rna.index(nucleotide)] if type == 'rna'
      return complement  
    rescue TypeError => e
      raise ArgumentError   
    end    
  end
end
