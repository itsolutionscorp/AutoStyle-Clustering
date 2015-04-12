class Hamming
  def compute(strand_a, strand_b)
    max_length = [strand_a.length, strand_b.length].max

    (0...max_length).count do |index|
      strand_a[index] != strand_b[index]
    end
  end
end
