class Complement
  DNA_TO_RNA_MAPPING = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  RNA_TO_DNA_MAPPING = DNA_TO_RNA_MAPPING.invert

  def self.of_dna(strand)
    calculate(strand, DNA_TO_RNA_MAPPING)
  end

  def self.of_rna(strand)
    calculate(strand, RNA_TO_DNA_MAPPING)
  end

private_class_method
  def self.calculate(strand, mapping)
    strand.gsub /./ do |nucleotide|
      mapping[nucleotide]
    end
  end
end
