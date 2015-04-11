class InvalidCodonError < StandardError
end

class Translation
  CODONS = {
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

  def self.of_codon(codon)
    begin
      CODONS.fetch(codon)
    rescue
      raise InvalidCodonError
    end
  end

  def self.of_rna(strand)
    answer = []
    strand.chars.each_slice(3) do |codon|
      answer << of_codon(codon.join)
    end
    stop = answer.index('STOP')
    if stop
      answer[0..stop - 1]
    else
      answer
    end
  end
end
