class Array
  def accumulate
    result = []
    each do |element|
      result << yield(element)
    end
    result
  end
end
