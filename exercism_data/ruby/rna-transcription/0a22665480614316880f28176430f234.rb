class Complement
  def self.of_dna(dna_strand)
    swapper = DnaRnaSwapper.new(dna_strand)
    swapper.swap_to_rna
  end

  def self.of_rna(dna_strand)
    swapper = DnaRnaSwapper.new(dna_strand)
    swapper.swap_to_dna
  end

end

class DnaRnaSwapper
  def initialize(dna_strand)
    @dna_strand = dna_strand
  end

  def swap_to_rna
    dna_adeine_swapper
    cytosine_guanine_swapper
    thymidine_swapper
    return @dna_strand
  end

  def swap_to_dna
    cytosine_guanine_swapper
    rna_adeine_swapper
    uracil_swapper
    return @dna_strand
  end

  def rna_adeine_swapper
    @dna_strand.gsub!('A','T')
  end

  def dna_adeine_swapper
    @dna_strand.gsub!('A','U')
  end

  def cytosine_guanine_swapper
    @dna_strand.gsub!('C','X')
    @dna_strand.gsub!('G','C')
    @dna_strand.gsub!('X','G')
  end

  def thymidine_swapper
    @dna_strand.gsub!('T','A')
  end

  def uracil_swapper
    @dna_strand.gsub!('U','A')
  end
end
