class Hamming
  def self.compute(aa, bb)
    raise ArgumentError if aa.length != bb.length

    count = 0
    (0 ... aa.length).each do |i|
      count += 1 if aa[i] != bb[i]
    end

    count
  end
end
