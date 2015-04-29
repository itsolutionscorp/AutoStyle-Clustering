class Hamming
  def self.compute(strand_a, strand_b)
    hamming_code = 0

    last_index = (strand_a.length > strand_b.length ? strand_b.length : strand_a.length) - 1

    (0..last_index).each do |i|
      if strand_a[i] != strand_b[i]
        hamming_code += 1
      end
    end

    hamming_code
  end
end
