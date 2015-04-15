class Complement

  def self.of_dna sequence
    Sequence.new( sequence ).to_rna
  end

  def self.of_rna sequence
    Sequence.new( sequence ).to_dna
  end

  class Sequence

    attr_reader :nucleotides

    DNA_TO_RNA = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }

    RNA_TO_DNA = DNA_TO_RNA.invert

    InvalidDnaSequence = Class.new StandardError
    InvalidRnaSequence = Class.new StandardError

    def initialize strand
      @nucleotides = strand.chars
    end

    def to_rna
      transform_with(  DNA_TO_RNA ).join
    rescue KeyError
      raise InvalidDnaSequence, "#{ nucleotides.join } is not a valid DNA sequence"
    end

    def to_dna
      transform_with( RNA_TO_DNA ).join
    rescue KeyError
      raise InvalidRnaSequence, "#{ nucleotides.join } is not a valid RNA sequence"
    end

  private

    def transform_with replacements
      nucleotides.map { |nucleotide| replacements.fetch nucleotide }
    end

  end

end
