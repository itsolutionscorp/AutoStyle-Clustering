class Complement

  RNAMAP = { 'U' => 'A', 'C' => 'G', 'G' => 'C', 'A' => 'T' }
  DNAMAP = RNAMAP.invert

  def self.convert(map, input)
      result = ''
      input.each_char.with_index { |c,i| result[i] =  map[c] }
      return result
  end

  def self.of_rna(rna)
      convert(RNAMAP,rna)
  end

  def self.of_dna(dna)
      convert(DNAMAP,dna)
  end
end
