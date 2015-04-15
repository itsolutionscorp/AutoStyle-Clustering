class InvalidCodonError < StandardError; end

class Translation
  STOP = 'STOP'
  CODON_PROTEIN_MAP = {
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
    'UAA' => STOP,
    'UAG' => STOP,
    'UGA' => STOP
  }

  def self.of_codon(codon)
    CODON_PROTEIN_MAP.fetch(codon) { raise InvalidCodonError }
  end

  def self.of_rna(strand)
    strand.scan(/\w{3}/).each_with_object([]) do |codon, proteins|
      protein = of_codon(codon)
      break proteins if protein == STOP
      proteins << protein
    end
  end
end
