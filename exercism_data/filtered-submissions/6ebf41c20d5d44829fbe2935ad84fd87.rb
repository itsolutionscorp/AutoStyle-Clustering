class Hamming
  def compute(first, second)
    shortest_length = [first.length, second.length].min
    (0...shortest_length).inject(0) do |distance, index|
      distance += (first[index] == second[index]) ? 0 : 1
    end
  end
end
