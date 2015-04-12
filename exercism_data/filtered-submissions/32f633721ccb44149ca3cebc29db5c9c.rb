class Hamming
  def compute(source, mutation)
    shortest_length = [source, mutation].map(&:length).min

    shortest_length.times.count do |i|
      source[i] != mutation[i]
    end
  end
end
