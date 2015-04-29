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
      DNA_TO_RNA.invert[rna]
    end
  end
end
