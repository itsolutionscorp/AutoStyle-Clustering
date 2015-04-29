class Series
  def initialize input
    @number = input
  end

  def slices n
    raise ArgumentError if n > @number.length
    sliced_char = (0..@number.length - n).map{ |num| @number.byteslice(num, n).chars}
    sliced_char.map{ |series_string| series_string.map(&:to_i) }
  end
end
