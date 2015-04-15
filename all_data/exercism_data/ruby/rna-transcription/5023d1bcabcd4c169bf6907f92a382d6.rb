class Complement

  DNA_TO_RNA_MAP = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_TO_DNA_MAP = {
    'C' => 'G',
    'G' => 'C',
    'A' => 'T',
    'U' => 'A'
  }

  def self.of_dna(strand)
    strand.chars.reduce('') do |out, input|
      out + DNA_TO_RNA_MAP[input]
    end
  end

  def self.of_rna(strand)
    strand.chars.reduce('') do |out, input|
      out + RNA_TO_DNA_MAP[input]
    end
  end
end
