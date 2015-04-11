class Complement

  def initialize(sequence, conversion_to)
    @sequence = sequence
    @conversion_to = conversion_to
  end

  def self.of_dna(sequence)
    new(sequence, "rna").of_dna
  end

  def self.of_rna(sequence)
    new(sequence, "dna").of_rna
  end

  def of_dna
    nucleotide.find
  end

  def of_rna
    nucleotide.find
  end

  private

  attr_reader :sequence
  attr_reader :conversion_to

  def nucleotide
    Nucleotide.new(sequence, conversion_to)
  end

end

class Nucleotide

  def initialize(sequence, conversion_to)
    @sequence = sequence
    @conversion_to = conversion_to
  end

  def find
    sequence.map { |char| type.fetch(char) }.join
  end

  def type
    if @conversion_to == "rna"
      dna_rna_pairings 
    else
      rna_dna_pairings
    end
  end

  private

  def sequence
    @sequence.chars
  end

  def dna_rna_pairings
    {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
  end

  def rna_dna_pairings
    dna_rna_pairings.invert
  end

end
