class Hamming

  def self.compute(first, second)
    return 0 if first == second

    min_length = [first, second].map(&:length).min
    0.upto(min_length-1).count { |i| first[i] != second[i] }
  end

end
