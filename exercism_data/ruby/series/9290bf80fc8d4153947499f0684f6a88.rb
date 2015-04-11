class Series
  def initialize(series)
    @series = series
  end
  
  def slices(num)
    if @series.length < num
      raise ArgumentError
    else
    series_array = @series.chars.map! { |string| string.to_i }
    slices = []
      until series_array.empty? do
      slices << series_array[0, num]
      series_array.shift
      end
    end
    slices.delete_if { |array| array.length != num }
  end
end
