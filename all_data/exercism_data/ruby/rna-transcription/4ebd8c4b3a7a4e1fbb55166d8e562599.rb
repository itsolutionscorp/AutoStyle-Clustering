class Complement
  def self.initialize_hash type
    if type == 'rna'
      complement_hash = {
        'G' => 'C',
        'C' => 'G',
        'A' => 'T',
        'U' => 'A'
      }
    else
      complement_hash = {
        'G' => 'C',
        'C' => 'G',
        'T' => 'A',
        'A' => 'U'
      }
    end
  end

  def self.of_dna nucleotides
    process(nucleotides, 'dna')
  end

  def self.of_rna nucleotides
    process(nucleotides, 'rna')
  end

  def self.process nucleotides,type
    comp_hash = initialize_hash(type)
    complment_string = ''
    nucleotides.chars.each do |nucleotide|
      complment_string += comp_hash["#{nucleotide}"]
    end
    complment_string
  end
end
