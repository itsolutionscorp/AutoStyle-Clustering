class Complement

  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U',
  }

  RNA_TO_DNA = DNA_TO_RNA.invert

  class << self
    def of_dna(dna)
      assert_valid_nucleotides!(dna, DNA_TO_RNA.keys)
      transcribe(dna, DNA_TO_RNA)
    end

    def of_rna(rna)
      assert_valid_nucleotides!(rna, RNA_TO_DNA.keys)
      transcribe(rna, RNA_TO_DNA)
    end

    private
    def transcribe(strand, mapping)
      strand.split('').map { |c| mapping.fetch(c) }.join('')
    end

    def assert_valid_nucleotides!(strand, allowed_nucleotides)
      invalid_nucleotides = strand.split('').uniq - allowed_nucleotides

      raise ArgumentError.new(
        "Invalid nucleotides: #{invalid_nucleotides.inspect}"
      ) if invalid_nucleotides.any?
    end
  end
end
