class Hamming

  def self.compute(oligonucleotide, mutation)
    oligonucleotide.chars.zip(mutation.chars).select do |oligonucleotide, mutation|
      hybridization_error?(oligonucleotide, mutation)
    end.count
  end

  def self.hybridization_error?(oligonucleotide, mutation)
    (oligonucleotide and mutation) and (oligonucleotide != mutation)
  end
end
