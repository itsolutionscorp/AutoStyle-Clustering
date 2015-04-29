class Hamming
  def self.compute(strand0, strand1)
    min = [strand0.length, strand1.length].min
    strand0, strand1 = strand0[0...min], strand1[0...min]
    strand0.split(//).each_with_index.count {|c, i| c != strand1[i]}
  end
end
