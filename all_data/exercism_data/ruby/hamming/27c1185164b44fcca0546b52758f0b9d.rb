class Hamming
  def self.compute(strand1, strand2)
    diff, ind = 0, 0
    strand1.split("").each do |i|
      if (strand1[ind] != strand2[ind]) && (strand2[ind] != nil)
        diff += 1
      end
      ind += 1
    end
    diff
  end
end
