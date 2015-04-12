class Hamming
  def compute(strand1, strand2)
    difference = 0
    length = [strand1.length, strand2.length].min
    (0..length-1).each do |count|
      difference += 1 if strand1[count] != strand2[count]
    end
    difference
  end
end
