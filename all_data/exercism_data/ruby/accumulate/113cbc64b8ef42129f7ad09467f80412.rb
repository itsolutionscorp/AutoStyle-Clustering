class Array

  def accumulate(&operation)
    result = []
    each do |x|
      result << yield(x)
    end
    if result.is_a? String
      result.operation
    else
      result
    end
  end



end
