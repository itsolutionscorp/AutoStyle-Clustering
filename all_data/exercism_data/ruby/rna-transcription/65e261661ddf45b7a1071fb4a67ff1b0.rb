module Complement
  DNA_MAPPINGS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }


  def self.of_dna dna
    dna.chars.map{|n| dna_mappings[n]}.join
  end

  def self.of_rna rna
    rna.chars.map{|n| rna_mappings[n]}.join
  end

private

  def self.dna_mappings
    DNA_MAPPINGS
  end

  def self.rna_mappings
    @rnam ||= reverse_mapping(dna_mappings)
  end

  def self.reverse_mapping(mapping)
    Hash[*mapping.map{|k,v| [v,k]}.flatten]
  end
end
