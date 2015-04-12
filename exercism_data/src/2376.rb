class Hamming
  def compute(strand1,strand2)
    (0..strand1.length).inject(0) do |distance,index|
      distance += 1 unless strand1[index] == strand2[index]
      distance
    end
  end
end
