class Complement
  def self.of_dna(dna_strand)
    transcribe(dna_strand, DNA)
  end

  def self.of_rna(rna_strand)
    transcribe(rna_strand, RNA)
  end

  def self.transcribe(strand, strand_type)
    other_strand = ''
    strand.each_char do |nucleotide|
      other_strand << strand_type.to_other(nucleotide)
    end

    other_strand
  end
end

class DNA
  ADENINE = 'A'
  CYTOSINE = 'C'
  GUANINE = 'G'
  THYMINE = 'T'

  def self.to_rna(dna)
    case dna
    when ADENINE
      return RNA::URACIL
    when CYTOSINE
      return RNA::GUANINE
    when GUANINE
      return RNA::CYTOSINE
    when THYMINE
      return RNA::ADENINE
    end

    raise ArgumentError, 'dna must be a valid DNA nucleotide'
  end
  def self.to_other(dna)
    to_rna(dna)
  end
end

class RNA
  ADENINE = 'A'
  CYTOSINE = 'C'
  GUANINE = 'G'
  URACIL = 'U'

  def self.to_dna(rna)
    case rna
    when ADENINE
      return DNA::THYMINE
    when CYTOSINE
      return DNA::GUANINE
    when GUANINE
      return DNA::CYTOSINE
    when URACIL
      return DNA::ADENINE
    end

    raise ArgumentError, 'rna must be a valid RNA nucleotide'
  end
  def self.to_other(rna)
    to_dna(rna)
  end
end
