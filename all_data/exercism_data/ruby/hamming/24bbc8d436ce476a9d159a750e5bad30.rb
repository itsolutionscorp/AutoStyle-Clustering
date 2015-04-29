class Hamming
  def self.compute dna_strand_a, dna_strand_b
    strand_a, strand_b = reject_extra_nucleotides dna_strand_a, dna_strand_b
  
    nucleotide_pairs = zip_dna_strands strand_a, strand_b
    
    count_mutations nucleotide_pairs
  end
  
  def self.reject_extra_nucleotides strand_a, strand_b
    length = [strand_a.length, strand_b.length].min
    [strand_a.slice(0, length), strand_b.slice(0, length)]
  end
  
  def self.zip_dna_strands strand_a, strand_b  
    nucleotides(strand_a).zip nucleotides(strand_b)
  end
  
  def self.nucleotides dna_strand
    dna_strand.split ''
  end
  
  def self.count_mutations nucleotide_pairs
    nucleotide_pairs.count {|nucl_a, nucl_b| nucl_a != nucl_b}
  end
end
