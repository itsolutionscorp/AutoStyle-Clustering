class Hamming
  def self.compute(strand, other_strand)
    compares = strand.chars.take(other_strand.length).zip(other_strand.chars)
    compares.count do |base, other_base|
      base != other_base
    end
  end
end
