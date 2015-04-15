class Hamming
  def self.compute(a, b)
    start_distance = 0
    length = [a.length, b.length].min

    (0...length).reduce(start_distance) do |distance, index|
      difference = (a[index] == b[index]) ? 0 : 1

      distance + difference
    end
  end
end
