class Series
  def initialize(num)
    @num = num
  end

  def slices(series_length)
    raise ArgumentError if series_length > @num.to_s.length
    series_list = []
    num_chars_array = @num.chars
    num_chars_array.each_with_index do |start, index|
      term = num_chars_array[index...(index + series_length)]
      term_integers = term.map {|x| x.to_i}
      series_list << term_integers if term.length == series_length
    end
    return series_list
  end
end
