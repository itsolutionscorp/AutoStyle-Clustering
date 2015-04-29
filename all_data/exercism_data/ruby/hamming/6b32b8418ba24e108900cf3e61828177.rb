class Hamming

  def self.compute(oligonucleotide_x, oligonucleotide_y)
    oligonucleotide_x.chars.zip(oligonucleotide_y.chars).count do |oligonucleotide_x, oligonucleotide_y |
      hybridization_error?(oligonucleotide_x, oligonucleotide_y)
    end
  end

  def self.hybridization_error?(oligonucleotide_x, oligonucleotide_y)
    (oligonucleotide_x && oligonucleotide_y) && (oligonucleotide_x != oligonucleotide_y)
  end
end
