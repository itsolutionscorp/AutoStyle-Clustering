class Complement

  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U',
  }

  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(dna)
    self.assert_valid_nucleotides!(dna, DNA_TO_RNA.keys)

    dna.split('').map { |c| DNA_TO_RNA.fetch(c) }.join('')
  end

  def self.of_rna(rna)
    self.assert_valid_nucleotides!(rna, RNA_TO_DNA.keys)

    rna.split('').map { |c| RNA_TO_DNA.fetch(c) }.join('')
  end

  def self.assert_valid_nucleotides!(strand, allowed_nucleotides)
    invalid_nucleotides = strand.split('').uniq - allowed_nucleotides

    raise ArgumentError.new(
      "Invalid nucleotides: #{invalid_nucleotides.inspect}"
    ) if invalid_nucleotides.any?
  end
end
