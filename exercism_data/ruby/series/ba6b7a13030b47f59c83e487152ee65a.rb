class Series
  def initialize (string)
    @string = string
  end

  def slices (number)
    raise ArgumentError if number > @string.length
    start = 0
    finish = number - 1
    sliced = []
    until finish == @string.length
      slice = []
      (start..finish).each do |i|
        slice << @string[i].to_i
      end
      start += 1
      finish +=1
      sliced << slice
    end
    sliced
  end
end
