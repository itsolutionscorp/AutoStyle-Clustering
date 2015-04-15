class Translation
  def self.of_codon(codon)
    protein = convert_to_protein[codon]
    fail InvalidCodonError if protein.nil?
    protein
  end

  def self.of_rna(strand)
    strand.scan(/.../).reduce([]) do |ary, codon|
      protein = of_codon(codon)
      return ary if protein == 'STOP'
      ary << protein
    end
  end

  def self.convert_to_protein
    {
       'AUG' => 'Methionine',
       'UUU' => 'Phenylalanine',
       'UUC' => 'Phenylalanine',
       'UUA' => 'Leucine',
       'UUG' => 'Leucine',
       'UCU' => 'Serine',
       'UCC' => 'Serine',
       'UCA' => 'Serine',
       'UCG' => 'Serine',
       'UAU' => 'Tyrosine',
       'UAC' => 'Tyrosine',
       'UGU' => 'Cystine',
       'UGC' => 'Cystine',
       'UGG' => 'Tryptophan',
       'UAA' => 'STOP',
       'UAG' => 'STOP',
       'UGA' => 'STOP'
     }
  end
end

class InvalidCodonError < ArgumentError; end
