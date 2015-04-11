module Translation

  def self.of_codon(codon)
    case codon
    when 'AUG' then 'Methionine'
    when 'UUU', 'UUC' then 'Phenylalanine'
    when 'UUA', 'UUG' then 'Leucine'
    when 'UCU', 'UCC', 'UCA', 'UCG' then 'Serine'
    when 'UAU', 'UAC' then 'Tyrosine'
    when 'UGU', 'UGC' then 'Cystine'
    when 'UGG' then 'Tryptophan'
    when 'UAA', 'UAG', 'UGA' then 'STOP'
    end
  end

  def self.of_rna(strand)
    proteins = []
    strand.scan(/.../).map do |codon|
      raise InvalidCodonError unless valid_rna?(codon)

      protein = of_codon(codon)
      return proteins if protein == 'STOP'
      proteins << protein
    end
    proteins
  end

  private

  RNA = %w(A U C G)

  def self.valid_rna?(rna)
    rna.chars.all? { |c| RNA.include?(c) }
  end

end

class InvalidCodonError < Exception; end
