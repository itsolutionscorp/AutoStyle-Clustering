class Hamming
  def self.compute(strand1, strand2)
    hamming_diff = 0

    strand1.chars.with_index do |base, index|
      break if strand2[index] == nil
      hamming_diff += 1 if base != strand2[index]
    end

    hamming_diff
  end
end
