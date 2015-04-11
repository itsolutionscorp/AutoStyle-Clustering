class Hamming
  def self.compute(strand1, strand2)
    diff = 0
    ind = 0
    strand1.split("").each do |i|
      puts strand1[ind]
      if (strand1[ind] != strand2[ind]) && (strand1[ind] != nil) && (strand2[ind] != nil)
        diff += 1
      end
      ind += 1
    end
    diff
  end
end
