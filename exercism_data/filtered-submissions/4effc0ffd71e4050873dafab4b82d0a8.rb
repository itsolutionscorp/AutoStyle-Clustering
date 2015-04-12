class Hamming
  def compute(first, second)
    shorter = first.length <= second.length ? first.length : second.length
    distance = 0
    (0...shorter).each do |i|
      first[i] == second[i] ? next : distance += 1
    end
    distance
  end
end
