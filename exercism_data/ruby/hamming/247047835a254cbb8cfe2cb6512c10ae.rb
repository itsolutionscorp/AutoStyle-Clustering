class Hamming

  def self.compute(strand1,strand2)

    # only compare if strands are same length
    if strand1.length === strand2.length
      #return count of differences
      [strand1.chars, strand2.chars].transpose.select { |chars1, chars2| chars1 != chars2}.count
    end

  end

end
