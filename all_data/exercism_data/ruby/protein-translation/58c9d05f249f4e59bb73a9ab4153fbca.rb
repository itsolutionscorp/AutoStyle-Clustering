InvalidCodonError = Class.new(StandardError)

class Translation
  def self.data
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

  def self.of_codon(codon)
    begin
      data.fetch(codon)
    rescue KeyError
      raise InvalidCodonError
    end
  end

  def self.of_rna(strand)
    codons = strand.chars.each_slice(3).map(&:join)
    proteins = codons.map { |codon| of_codon(codon) }
    proteins.take_while { |p| p != 'STOP' }
  end
end
