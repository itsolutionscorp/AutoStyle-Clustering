class Hamming
  attr_reader :paired_strands

  def self.compute strand, other_strand
    new(strand, other_strand).hamming_distance
  end

  def initialize strand, other_strand
    short_strand, long_strand = [ strand.chars, other_strand.chars ]
                                .sort_by(&:length)
    @paired_strands = short_strand.zip(long_strand)
  end

  def hamming_distance
    paired_strands.count { |nucleo1, nucleo2| mutation?(nucleo1, nucleo2) }
  end

  def mutation? nucleo1, nucleo2
    nucleo1 != nucleo2
  end
end
