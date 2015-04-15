class Translation
  def initialize(sequences)
    @codons = parse_to_codons(sequences)
    fail InvalidCodonError unless valid?
  end

  def translate
    idx = proteins.index('STOP') || proteins.length
    idx == 0 ? proteins[0..0] : proteins[0..(idx - 1)]
  end

  def proteins
    @proteins = [].tap do |proteins|
      @codons.each do |codon|
        proteins << codon_to_protein[codon]
      end
    end
  end

  def self.of_codon(sequences)
    new(sequences).translate.join
  end

  def self.of_rna(sequences)
    new(sequences).translate
  end

  private

  def valid?
    @codons.all? { |codon| codon_to_protein.keys.include? codon }
  end

  def parse_to_codons(sequences)
    sequences.chars.each_slice(3).to_a.map(&:join).map(&:to_sym)
  end

  def codon_to_protein
    {
      AUG: 'Methionine',
      UUU: 'Phenylalanine',
      UUC: 'Phenylalanine',
      UUA: 'Leucine',
      UUG: 'Leucine',
      UCU: 'Serine',
      UCC: 'Serine',
      UCA: 'Serine',
      UCG: 'Serine',
      UAU: 'Tyrosine',
      UAC: 'Tyrosine',
      UGU: 'Cystine',
      UGC: 'Cystine',
      UGG: 'Tryptophan',
      UAA: 'STOP',
      UAG: 'STOP',
      UGA: 'STOP'
    }
  end
end
class InvalidCodonError < StandardError; end
