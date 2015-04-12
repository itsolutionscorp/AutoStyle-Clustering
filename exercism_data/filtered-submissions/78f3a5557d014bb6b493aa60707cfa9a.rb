class Hamming
  def compute(strand1,strand2)
    strand1.length.times.count { |index| strand1[index] != strand2[index] }
  end
end
