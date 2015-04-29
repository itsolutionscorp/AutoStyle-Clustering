class Complement

  @dna_dict={}
  @dna_dict['G'] = 'C'
  @dna_dict['C'] = 'G'
  @dna_dict['T'] = 'A'
  @dna_dict['A'] = 'U'

  @rna_dict={}
  @rna_dict['C'] = 'G'
  @rna_dict['G'] = 'C'
  @rna_dict['A'] = 'T'
  @rna_dict['U'] = 'A'

  def self.of_dna rna
    if rna.length == 1
      @dna_dict[rna.upcase]
    else
      @dna_dict[rna[0]] + self.of_dna(rna[1..-1])
    end
  end

  def self.of_rna dna
    if dna.length == 1
      @rna_dict[dna.upcase]
    else
      @rna_dict[dna[0]] + self.of_rna(dna[1..-1])
    end
  end

end
