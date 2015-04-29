Strand = Struct.new(:sequence) do
  DNA_RNA_CONVERSION = {
    'G' => 'C', 
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def to_rna
    sequence.split('').map {|x| DNA_RNA_CONVERSION[x] }.join
  end

  def to_dna
    conversion = DNA_RNA_CONVERSION.invert
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
