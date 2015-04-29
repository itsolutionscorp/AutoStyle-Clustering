class Hamming
  def self.compute(expected, mutation)
    counter = 0
    shortest_length = [expected.length, mutation.length].min
    (0...shortest_length).each do |index|
      if expected[index] != mutation[index]
        counter += 1
      end
    end
    counter
  end
end
