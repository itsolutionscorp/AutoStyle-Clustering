class Hamming
  def self.compute(a, b)
    strand_a = a.split('')
    strand_b = b.split('')

    diff = strand_a.keep_if do |nucleotide_a|
      nucleotide_b = strand_b.shift
      nucleotide_b && (nucleotide_a != nucleotide_b)
    end

    diff.size
  end
end
