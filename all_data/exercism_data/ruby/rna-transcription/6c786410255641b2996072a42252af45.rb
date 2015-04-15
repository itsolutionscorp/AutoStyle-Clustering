class Complement
  def self.of_dna(strand)
    raise ArgumentError if strand =~ /[^CGTA]/
    strand.tr('CGTA', 'GCAU')
  end

  def self.of_rna(strand)
    raise ArgumentError if strand =~ /[^CGUA]/
    strand.tr('CGUA', 'GCAT')
  end
end
