class Hamming

  def self.compute(strand1, strand2)
    Hamming::Nucleotide.new(strand1).compare(Hamming::Nucleotide.new(strand2))
  end

  class Nucleotide
    attr_reader :strand

    def initialize(strand)
      @strand = strand
    end

    def compare(nucleotide)
      len = [strand.size, nucleotide.strand.size].min
      (0...len).count {|i| strand[i] != nucleotide.strand[i]}
      # distance = 0
      # strand.split("").each_with_index do |c,i|
      #   return distance if nucleotide.strand[i].nil?
      #   next unless c != nucleotide.strand[i]
      #   distance += 1
      # end
      # distance
    end
  end
end
