class Complement
  
  def self.of_dna(dna)
  	if dna.match(/[^GCTA]/)
  	  raise ArgumentError, 'Argument has a not the right nucleotides that are found in DNA'
  	else
      dna.gsub(/[GCTA]/, 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U')
    end
  end

  def self.of_rna(rna)
  	if rna.match(/[^CGAU]/)
  	  raise ArgumentError, 'Argument has a not the right nucleotides that are found in RNA'
  	else
      rna.gsub(/[CGAU]/, 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A')
    end
  end

end
