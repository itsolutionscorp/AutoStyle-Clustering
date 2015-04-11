class Complement

  RNA_COMPLEMENT = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U'
  }

  DNA_COMPLEMENT = {
    'C' => 'G',
    'G' => 'C',
    'U' => 'A',
    'A' => 'T'
  }

  def self.of_dna(dna)
    complement = ''
    dna.chars.each do |el|
    raise ArgumentError if RNA_COMPLEMENT[el] == nil
      complement << RNA_COMPLEMENT[el]
    end
    return complement
  end

  def self.of_rna(rna)
    complement = ''
    rna.chars.each do |el|
    raise ArgumentError if DNA_COMPLEMENT[el] == nil
      complement << DNA_COMPLEMENT[el]
    end
    return complement
  end
end
