class Hamming
  def self.compute(s, t)
    dataset = parse_dataset(s,t)

    distance(dataset)
  end

  private

  # @param [String] s first  dataset
  # @param [String] t second dataset
  # @return [Array] two dimensional. One for each dataset.
  def self.parse_dataset(s, t)
    [s, t].map { |d| d.split('') }
  end

  # @param [Array] dataset as two dimensional array
  # @return [Fixnum] hamming_distance
  def self.distance(dataset)
    hamming_distance = 0

    dataset[0].each_index do |i|
      next if dataset[0][i] == dataset[1][i]

      hamming_distance += 1
    end

    hamming_distance
  end
end
