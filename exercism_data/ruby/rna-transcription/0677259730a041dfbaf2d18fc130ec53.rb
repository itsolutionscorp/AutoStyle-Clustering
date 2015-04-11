class Complement
  def self.of_dna(sequence)
    Dna.new(sequence).rna_complement
  end

  def self.of_rna(sequence)
    Rna.new(sequence).dna_complement
  end

  class Dna
    attr_reader :sequence

    def initialize(sequence)
      @sequence = sequence
    end

    def rna_complement
      sequence.tr "ACGT", "UGCA"
    end
  end

  class Rna
    attr_reader :sequence

    def initialize(sequence)
      @sequence = sequence
    end

    def dna_complement
      sequence.tr "ACGU", "TGCA"
    end
  end
end
