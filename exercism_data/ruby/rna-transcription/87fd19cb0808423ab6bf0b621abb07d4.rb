module Complement
  DNA_MAPPINGS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna dna
    map(dna,dna_mappings)
  end

  def self.of_rna rna
    map(rna,rna_mappings)
  end

private

  def self.map str,mapping
    str.chars.map{|n| mapping[n]}.join
  end

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
