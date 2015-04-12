class Hamming
  def compute(strand1, strand2)
    (0...strand1.length).reduce(0) do |hamming, i|
      hamming += 1 if strand1[i] != strand2[i]
      hamming
    end
  end
end
