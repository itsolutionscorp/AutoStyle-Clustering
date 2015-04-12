class Hamming
  def compute(a,b)
    distance = 0
    (0..a.length).each do |index|
      break unless a[index] && b[index]
      distance += 1 unless a[index] == b[index]
    end
    distance
  end
end
