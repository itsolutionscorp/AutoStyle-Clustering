module Hamming

  def self.compute(dna1, dna2)
    if dna1.length != dna2.length
      raise ArgumentError.new('DNA strands must be equal lengths.')
    end

    (0...dna1.length).count { |idx| dna1[idx] != dna2[idx] }
  end

end
