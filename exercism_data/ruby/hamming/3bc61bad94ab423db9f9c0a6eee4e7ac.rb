class Hamming

  def self.compute(strand1, strand2)
    delta = shared_strand_length(strand1, strand2).times.inject(0) { |mem, index|
      strand1[index] != strand2[index] ? mem + 1 : mem
    }
  end

  def self.shared_strand_length(strand1, strand2)
    [strand1.length, strand2.length].min
  end

end
