class Hamming
  def compute(strand0, strand1)
    distance = 0
    min = [strand0.length, strand1.length].min
    strand0, strand1 = strand0[0...min], strand1[0...min]
    strand0.split(//).each_with_index do |c, i|
      distance += 1 unless c == strand1[i]
    end
    distance
  end
end
