class Hamming
  def self.compute(nucleotide1, nucleotide2)
    diffs = 0
    nucleotide1.length.times do |i|
      diffs += 1 if nucleotide1[i] != nucleotide2[i]
    end
    diffs
  end
end
