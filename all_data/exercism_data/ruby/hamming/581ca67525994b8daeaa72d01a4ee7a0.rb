class Hamming
  def self.compute dna_strand_a, dna_strand_b
    nucleotide_pairs = zip_dna_strands dna_strand_a, dna_strand_b
    
    mutation_count = nucleotide_pairs.count {|nucl_a, nucl_b| nucl_a != nucl_b}
  end
  
  def self.zip_dna_strands strand_a, strand_b  
    nucleotides(strand_a).zip(nucleotides(strand_b)).reject do |nucl_a, nucl_b|
      nucl_a.nil? || nucl_b.nil?
    end
  end
  
  def self.nucleotides dna_strand
    dna_strand.split ''
  end
end
