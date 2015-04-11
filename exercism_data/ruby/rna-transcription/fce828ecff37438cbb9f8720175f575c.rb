class Complement

  def self.of_dna(strand)
    new('dna', strand).strand_complement
  end

  def self.of_rna(strand)
    new('rna', strand).strand_complement
  end

  attr_reader :molecule,
              :strand

  def initialize(molecule, strand)
    @molecule = molecule
    @strand   = strand
  end

  def strand_complement
    if molecule == 'dna'
      strand.split("").map {|n| get_dna_complement(n)}.join
    else
      strand.split("").map {|n| get_rna_complement(n)}.join
    end
  end

  private

  def get_dna_complement(nucleotide)
    case nucleotide
    when 'C'
      'G'
    when 'G'
      'C'
    when 'T'
      'A'
    when 'A'
      'U'
    end
  end

  def get_rna_complement(nucleotide)
    case nucleotide
    when 'C'
      'G'
    when 'G'
      'C'
    when 'U'
      'A'
    when 'A'
      'T'
    end
  end

end
