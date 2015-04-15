class Hamming
  def self.compute(strand_one, strand_two)
    strand_one.chars.zip(strand_two.chars).count{ |pair| pair.compact.uniq.size > 1 }
  end
end
