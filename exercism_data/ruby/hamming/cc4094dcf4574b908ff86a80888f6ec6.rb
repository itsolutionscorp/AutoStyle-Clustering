class Hamming
  def self.compute(strand_a = '', strand_b = '')
    distance = 0
    longer_strand, shorter_strand = compare_strand_lengths(strand_a, strand_b)

    shorter_strand.each_with_index do |char, index|
      distance += 1 if char != longer_strand[index]
    end

    distance
  end

  private

  def self.compare_strand_lengths(strand_a, strand_b)
    if strand_a.length >= strand_b.length
      return strand_a.split(''), strand_b.split('')
    else
      return strand_b.split(''), strand_a.split('')
    end
  end
end
