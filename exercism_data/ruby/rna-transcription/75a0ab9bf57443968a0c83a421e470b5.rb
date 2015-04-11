class Complement
  def self.of_dna dna_sequence

    rna_sequence = ""
    dna_necleotide = ""
    rnd_nucleotide = ""

    dna_sequence.length.times do |index|
      dna_nucleotide = dna_sequence[index]

      case dna_nucleotide
        when "G"
          rna_nucleotide = "C"
        when "C"
          rna_nucleotide = "G"
        when "T"
          rna_nucleotide = "A"
        when "A"
          rna_nucleotide = "U"
      end

      rna_sequence += rna_nucleotide
    end
    return rna_sequence
  end

  def self.of_rna rna_sequence

    dna_sequence = ""
    dna_necleotide = ""
    rnd_nucleotide = ""

    rna_sequence.length.times do |index|
      rna_nucleotide = rna_sequence[index]

      case rna_nucleotide
      when "G"
        dna_nucleotide = "C"
      when "C"
        dna_nucleotide = "G"
      when "A"
        dna_nucleotide = "T"
      when "U"
        dna_nucleotide = "A"
      end

      dna_sequence += dna_nucleotide
    end
    return dna_sequence
  end

end
