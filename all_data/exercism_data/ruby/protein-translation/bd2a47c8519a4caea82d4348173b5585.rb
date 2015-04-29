class InvalidCodonError < ArgumentError; end

class Translation
  PROTEINS = {
    'Methionine'    => %w(AUG),
    'Phenylalanine' => %w(UUU UUC),
    'Leucine'       => %w(UUA UUG),
    'Serine'        => %w(UCU UCC UCA UCG),
    'Tyrosine'      => %w(UAU UAC),
    'Cystine'       => %w(UGU UGC),
    'Tryptophan'    => %w(UGG),
    'STOP'          => %w(UAA UAG UGA)
  }
  CODONS = PROTEINS.each_with_object({}) do |(protein, codons), h|
    codons.each { |codon| h[codon] = protein }
  end

  def self.of_codon(codon)
    CODONS.fetch(codon) { fail InvalidCodonError, "`#{codon}' is not a codon" }
  end

  def self.of_rna(strand)
    strand.scan(/.{1,3}/).map { |codon| of_codon codon }
      .take_while { |codon| codon != 'STOP' }
  end
end
