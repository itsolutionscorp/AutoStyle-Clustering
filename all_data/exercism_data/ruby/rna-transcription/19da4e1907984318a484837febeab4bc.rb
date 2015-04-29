class Complement

  def self.of_rna(rna_strand)
    rna_nucleotides = rna_strand.chars

    rna_nucleotides.each_with_object("") do |rna_nucleotide, dna_strand|
      compliment = RnaNucleotide.new(rna_nucleotide).dna_counterpoint
      dna_strand << compliment
    end
  end

  def self.of_dna(dna_strand)
    dna_nucleotides = dna_strand.chars

    dna_nucleotides.each_with_object("") do |dna_nucleotide, rna_strand|
      if dna_nucleotide == "C"
        rna_strand << "G"
      elsif dna_nucleotide == "T"
        rna_strand <<  "A"
      elsif dna_nucleotide == "A"
        rna_strand << "U"
      else
        rna_strand << "C"
      end
    end
  end
end

class RnaNucleotide < Struct.new(:nucleotide)
  def dna_counterpoint
    PAIRS[nucleotide]
  end

  PAIRS = { "G" => "C", "A" => "T", "U" => "A", "C" => "G" }.freeze
end
