class Series
  attr_reader :input
  def initialize(input)
    @input = input
  end

  def slices(series_length)
    raise ArgumentError if series_length > input.size

    n_digit_series = []
    input.length.times do |i|
      series_output = input.chars[i, series_length].map(&:to_i)
      break if series_output.size < series_length
      n_digit_series << series_output
    end

    n_digit_series
  end
end
