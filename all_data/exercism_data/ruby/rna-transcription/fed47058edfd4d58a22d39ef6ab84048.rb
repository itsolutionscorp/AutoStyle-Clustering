class Complement

  def self.of_dna(dna_strand)
    complement_of_strand(dna_strand, dna_transcriber)
  end

  def self.of_rna(rna_strand)
    complement_of_strand(rna_strand, rna_transcriber)
  end

  private

  @@dna_complements = { 'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U' }

  def self.complement_of_strand(strand, transcriber)
    complement = ""

    strand.each_char do |nucleotide|
      complement << transcriber.call(nucleotide)
    end

    complement
  end

  def self.dna_transcriber
    Proc.new { |nucleotide|
      @@dna_complements[nucleotide]
    }
  end

  def self.rna_transcriber
    Proc.new { | nucleotide|
      @@dna_complements.key(nucleotide)
    }
  end
end
