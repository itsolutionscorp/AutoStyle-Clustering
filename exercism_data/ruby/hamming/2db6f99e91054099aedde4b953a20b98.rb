class Hamming

  def self.compute(strand1, strand2)

    if strand1.length == strand2.length
      "Wrong length. The strands have to be the same length"
    end

    splitted_strand_1 = strand1.scan(/\w/)
    splitted_strand_2 = strand2.scan(/\w/)

    count = 0

    splitted_strand_1.each_with_index do |seq, i|
      count += 1 unless splitted_strand_2[i] == seq
    end

    count

  end

end
