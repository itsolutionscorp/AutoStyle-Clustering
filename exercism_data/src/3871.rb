class Hamming
  def compute(first, second)
    shorter = [first.length, second.length].min
    distance = 0
    (0...shorter).each do |i|
      distance += 1 unless first[i] == second[i]
    end
    distance
  end
end
