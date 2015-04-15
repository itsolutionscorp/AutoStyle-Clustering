class Array
  def keep 
    result = []
    each { |obj| result << obj if yield(obj)}
    result
  end

  def discard
    result = []
    each { |obj| result << obj if !yield(obj)}
    result
  end
end
