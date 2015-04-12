class Hamming
  def compute(first, second)
    min_size = [first.size, second.size].min
    (0...min_size).count {|i| first[i] != second[i] }
  end
end
