class Series
  def initialize(string)
    @string = string
  end

  def slices(slice_count)
    if slice_count <= @string.length
      output = []
      @string.chars.each_with_index do |number, i|
        output << @string.chars[i...(i+slice_count)].map { |num| num.to_i } if i + slice_count <= @string.length
      end
      output
    else
      raise ArgumentError
    end
  end
end
