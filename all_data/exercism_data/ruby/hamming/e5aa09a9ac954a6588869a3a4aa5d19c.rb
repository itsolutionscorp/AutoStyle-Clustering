class Hamming

  def self.compute(oligonucleotide_x, oligonucleotide_y)
    oligonucleotide_x.chars.zip(oligonucleotide_y.chars).count do |x, y|
      hybridization_error?(x, y)
    end
  end

  def self.hybridization_error?(x, y)
    (x && y) && (x != y)
  end
end
