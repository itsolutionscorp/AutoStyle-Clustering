module Complement
  def self.of_dna(strand)
    complements(strand, :dna)
  end

  def self.of_rna(strand)
    complements(strand, :rna)
  end

  class << self
    DNA_TO_RNA = {
      "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U",
    }

    RNA_TO_DNA = DNA_TO_RNA.invert

    private

    def complements(strand, type)
      complements = strand.each_char.map do |neucleotide|
        self.send(:"complement_to_#{type}", neucleotide)
      end
      complements.join
    end

    def complement_to_dna(dna)
      DNA_TO_RNA[dna]
    end

    def complement_to_rna(rna)
      RNA_TO_DNA[rna]
    end
  end
end
