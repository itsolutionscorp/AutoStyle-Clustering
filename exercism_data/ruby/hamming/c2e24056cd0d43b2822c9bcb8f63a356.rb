class Hamming
  def self.compute(strand_A, strand_B)
    strand_A.chars
      .zip(strand_B.chars)
        .delete_if {|_pair| _pair.last.nil?}
          .keep_if {|_pair| _pair.first != _pair.last}
          .count
  end
end
