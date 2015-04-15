class Complement
  COMPS = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

  def self.of_dna strand
    if strand.delete(COMPS.keys.join).empty?
      strand.chars.map {|n| COMPS[n]}.join
    else
      raise ArgumentError.new('Invalid nucleotide.')
    end
  end

  def self.of_rna strand
    if strand.delete(COMPS.invert.keys.join).empty?
      strand.chars.map {|n| COMPS.invert[n]}.join
    else
      raise ArgumentError.new('Invalid nucleotide.')
    end
  end
end
