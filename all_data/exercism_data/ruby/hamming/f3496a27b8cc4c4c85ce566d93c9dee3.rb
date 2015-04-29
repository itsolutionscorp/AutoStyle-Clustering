class Hamming

  def self.compute(strand1, strand2)
    i = 0
    difference_count = 0
    strand1, strand2 = strand1.split(//), strand2.split(//)

    strand1.each do |point|
      if point != strand2[i]
        difference_count += 1
      end
      i += 1
    end

    return difference_count
  end

end
