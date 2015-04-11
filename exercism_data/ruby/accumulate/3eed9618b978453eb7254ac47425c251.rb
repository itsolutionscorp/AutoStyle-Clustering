class Array
  def accumulate
    result = []
    each do |i|
      result << yield(i)
    end
    result
  end
end
