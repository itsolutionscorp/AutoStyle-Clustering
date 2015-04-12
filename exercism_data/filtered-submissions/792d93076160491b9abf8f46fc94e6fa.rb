class Hamming
  def compute(strand1, strand2)
    count = 0
    [strand1.length, strand2.length].min.times do |i|
      count += 1 if not (strand1[i] == strand2[i])
    end
    count
  end
end