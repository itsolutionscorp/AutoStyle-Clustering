class Complement
  DNA_TO_RNA = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  def self.of_dna org_strand
    toggle_nucleotides org_strand, DNA_TO_RNA  
  end

  def self.of_rna org_strand
    toggle_nucleotides org_strand, DNA_TO_RNA.invert 
  end

  def self.toggle_nucleotides org_strand, equivalence
    new_strand = Array.new
    org_strand.each_char do | n |
      raise ArgumentError.new(n + ' is not a valid nucleotide!') if equivalence[n] == nil
      new_strand.push(equivalence[n])
    end
    new_strand.join
  end

  private_class_method :toggle_nucleotides
end
