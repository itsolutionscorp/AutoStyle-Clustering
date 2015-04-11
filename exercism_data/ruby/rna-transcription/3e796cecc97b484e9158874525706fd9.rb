class Complement
  def self.dna_to_rna_mapping
    { 'C' => 'G',
      'G' => 'C',
      'T' => 'A',
      'A' => 'U' }
  end

  def self.of_dna( dna_string )
    rna_string = []
    dna_string.each_char { |x| rna_string << dna_to_rna_mapping[ x ]}
    rna_string.join
  end

  def self.of_rna( rna_string )
    dna_string = []
    rna_string.each_char { |x| dna_string << dna_to_rna_mapping.key( x )}
    dna_string.join
  end
end
