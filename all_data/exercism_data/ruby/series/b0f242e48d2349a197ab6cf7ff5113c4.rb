class Series

  def initialize(string)
    @string = string
  end

  def slices(of_length)
    if of_length > @string.size
      raise ArgumentError
    end
    result = []
    (0..@string.size-1).each do |i|
      current_slice = @string[i..i+of_length-1].split('').map {|ele| ele.to_i}
      if current_slice.size == of_length
        result << current_slice
      end
    end
    result
  end
end
