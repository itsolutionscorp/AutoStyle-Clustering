class Complement
  def self.of_dna(dna_strand) 
    complement_nucleotides = { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }
    rna_strand = ""

    dna_strand.each_char do |c|
      rna_strand << complement_nucleotides[c]
    end

    rna_strand
  end

  def self.of_rna(rna_strand)
    complement_nucleotides = { "C" => "G", "G" => "C", "A" => "T", "U" => "A" }
    dna_strand = ""

    rna_strand.each_char do |c|
      dna_strand << complement_nucleotides[c]
    end

    dna_strand
  end
end
