class Array
  def accumulate
    #map {|x| yield x}
    result = []
    each {|e| result << yield(e)}
    return result
  end
end
