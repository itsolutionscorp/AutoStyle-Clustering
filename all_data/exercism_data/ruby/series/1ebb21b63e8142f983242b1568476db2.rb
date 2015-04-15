class Series
  def initialize(series)
    @series = convert_series_to_int_array(series)
  end

  def slices(how_many)
    assert_sane_input(@series, how_many)
    slices = []

    @series.each_with_index() do |number, index|
      break if @series.length - index < how_many
      slices << @series[index...index + how_many]
    end
    slices
  end

  private
  def convert_series_to_int_array(series)
    series.split('').map(&:to_i)
  end

  def assert_sane_input(series, how_many)
    raise ArgumentError if how_many > @series.length
  end
end
