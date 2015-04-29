class DNA
  def initialize(nuc)
    @nuc = nuc.split('')
    dna_nucleotides = ['A', 'T', 'G', 'C']
    if (@nuc - dna_nucleotides).count >  0
      raise ArgumentError, "This is not a DNA protein!"
    end
  end

  def count(dna)
    if ['A', 'T','G','C', 'U'].include?(dna)
      @nuc.count{|x| x == dna}
    else
      raise ArgumentError, "Argument is not a DNA protein!"
    end
  end

  def nucleotide_counts
    hash= Hash.new(0)
    hash['A'] = self.count('A')
    hash['T'] = self.count('T')
    hash['C'] = self.count('C')
    hash['G'] = self.count('G')
    hash
  end

end
