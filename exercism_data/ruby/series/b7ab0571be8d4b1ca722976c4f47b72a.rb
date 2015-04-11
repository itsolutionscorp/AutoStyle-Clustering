class Series
  def initialize(string)
    @string = string
  end

  def slices(slice_count)
    if slice_count <= @string.length
      (0..(@string.length-slice_count)).map do |i|
        @string.chars[i...(i+slice_count)].map { |num| num.to_i }
      end
    else
      raise ArgumentError
    end
  end
end
