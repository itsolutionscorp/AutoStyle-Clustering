class Hamming
  def compute strand_1, strand_2
    strand_1
      .split(//)
      .zip(strand_2.split(//))
      .reduce(0) { |hamming, pair|
        if pair.last && pair.last != pair.first
          hamming + 1
        else
          hamming
        end
      }
  end
end
