class Complement

  @rnamap = { 'U' => 'A', 'C' => 'G', 'G' => 'C', 'A' => 'T' }

  def self.of_rna(rna)
      dna = ''
      rna.each_char.with_index { |c,i| dna[i] =  @rnamap[c] }
      return dna
  end

  def self.of_dna(dna)
      rna = ''
      dna.each_char.with_index { |c,i| rna[i] = (@rnamap.invert)[c] }
      return rna
  end
end
