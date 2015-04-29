class Complement
  COMPLEMENTS = {
    'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'
  }

  def self.of_dna(dna)
    dna.split('').inject('') do |strand, item|
      raise ArgumentError unless COMPLEMENTS.key? item
      strand + COMPLEMENTS[item]
    end
  end

  def self.of_rna(rna)
    rna.split('').inject('') do |strand, item|
      raise ArgumentError unless COMPLEMENTS.value? item
      strand + COMPLEMENTS.key(item)
    end
  end
end
