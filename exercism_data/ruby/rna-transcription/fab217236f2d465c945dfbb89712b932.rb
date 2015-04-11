class Complement
  @@DNA_to_RNA = {'A' => 'U', 'C' => 'G', 'G' => 'C', 'T' => 'A'} 
  @@RNA_to_DNA = @@DNA_to_RNA.invert

  def self.of_dna(dna)
    complement(dna, @@DNA_to_RNA)
  end

  def self.of_rna(rna)
    complement(rna, @@RNA_to_DNA)
  end

  def self.complement(s, m)
    c = s.chars.map {|x| m[x]}.join
    return c unless s.length != c.length
    # At least one character did not have a complement.
    raise 'Invalid strand.'
  end
end
