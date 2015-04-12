class Hamming
  def compute(strand, strand2)
    differences = 0

    strand.split(//).each_with_index do |char, index|
      differences += 1 if strand2[index] && char != strand2[index]
    end

    differences
  end
end
