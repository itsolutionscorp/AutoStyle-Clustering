Strand = Struct.new(:sequence) do
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
    sequence.split('').map {|x| conversion[x] }.join
  end
end

class Complement
  class << self
    def of_dna(sequence)
      Strand.new(sequence).to_rna
    end

    def of_rna(sequence)
      Strand.new(sequence).to_dna
    end
  end
end
