module Complement
  ADENINE = "A"
  CYTOSINE = "C"
  GUANINE = "G"
  THYMIDINE = "T"
  URACIL = "U"

  BASE_TRANSCRIPTION = {
    CYTOSINE => GUANINE,
    GUANINE => CYTOSINE,
    THYMIDINE => ADENINE,
    URACIL => ADENINE
  }

  def self.transcribe_with(nucleotide)
    Hash.new { |hash, key|
      if key == ADENINE
        hash[key] = nucleotide
      else
        hash[key] = BASE_TRANSCRIPTION[key]
      end
    }
  end

  RNA = transcribe_with(URACIL)

  DNA = transcribe_with(THYMIDINE)

  def self.of_dna(dna_strand)
    complement(dna_strand, RNA)
  end

  def self.of_rna(rna_strand)
    complement(rna_strand, DNA)
  end

  private

  def self.complement(strand, transcribe_key)
    strand.chars.reduce("") do |out_strand, nucleotide|
      out_strand << transcribe_key[nucleotide]
    end
  end

end
