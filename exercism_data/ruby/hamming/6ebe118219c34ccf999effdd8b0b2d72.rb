class Hamming
  def self.compute(source, mutation)
    shortest_length = [source, mutation].map(&:length).min
    distance = 0

    shortest_length.times do |i|
      if source[i] != mutation[i]
        distance += 1
      end
    end

    distance
  end
end
