class Hamming
  def compute(a,b)
    distance = 0
    a.split('').each_index do |i|
      break unless b[i]
      distance += 1 if a[i] != b[i]
    end
    distance
  end
end
