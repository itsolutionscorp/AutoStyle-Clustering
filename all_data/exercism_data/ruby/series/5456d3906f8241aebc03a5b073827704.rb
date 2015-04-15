class Series

  def initialize(sequence)
    @sequence = sequence
  end

  def slices(length)
    raise ArgumentError if length > @sequence.size

    number_of_results = @sequence.size - length
    (0..number_of_results).map do |index|
      @sequence.slice(index, length).chars.map(&:to_i)
    end
  end

end
