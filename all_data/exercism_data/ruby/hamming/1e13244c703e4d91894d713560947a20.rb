class Hamming
  def self.compute(strand1, strand2)
    find_shorter_strand(strand1, strand2)
    hamming_distance = 0

    @shorterstrand.each_char.each_with_index do |c, i|
      if @otherstrand.byteslice(i) != c && @shorterstrand.byteslice(i) != nil
        hamming_distance += 1
      end
    end
    return hamming_distance
  end

  def self.find_shorter_strand(strand1, strand2)
    if strand1.length < strand2.length
      @shorterstrand = strand1
      @otherstrand = strand2
    else
      @shorterstrand = strand2
      @otherstrand = strand1
    end
    return @shorterstrand
  end
end
