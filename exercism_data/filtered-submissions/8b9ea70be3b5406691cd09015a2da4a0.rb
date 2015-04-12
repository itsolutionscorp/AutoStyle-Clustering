class Hamming
  def compute(strand1,strand2)
    result = 0
    (0..strand1.length).each do |i|
      result += 1 if strand1[i] != strand2[i]
    end
    result
  end
end
