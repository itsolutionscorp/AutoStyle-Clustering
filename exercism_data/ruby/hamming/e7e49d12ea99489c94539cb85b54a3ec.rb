class Hamming
	def self.compute(first_string, second_string)
    counter = 0
    iterations = self.get_shortest_length(first_string, second_string)
    iterations.times do |i|
      counter += 1 if first_string[i] != second_string[i]
    end
    counter
	end

  def self.get_shortest_length(first_string, second_string)
    first_string.length < second_string.length ? first_string.length : second_string.length 
  end
end
