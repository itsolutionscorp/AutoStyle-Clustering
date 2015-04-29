Complement = Struct.new(:sequence) do
  def self.of_dna(sequence)
    new(sequence).to_rna
  end

  def self.of_rna(sequence)
    new(sequence).to_dna
  end

  DNA_RNA_CONVERSION = {
    'G' => 'C', 
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  RNA_DNA_CONVERSION = DNA_RNA_CONVERSION.invert

  def to_rna
    compliment(DNA_RNA_CONVERSION)
  end

  def to_dna
    compliment(RNA_DNA_CONVERSION)
  end

  private

  def compliment(conversion)
    sequence.chars.map {|x| conversion[x] }.join
  end
end
