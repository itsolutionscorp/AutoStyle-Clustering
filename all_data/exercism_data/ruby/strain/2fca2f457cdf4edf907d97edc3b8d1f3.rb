class Array

  def keep 
    result = []
    each {|elem| result << elem if yield(elem)}
    result
  end

  def discard
    result = []
    each {|elem| result << elem if !yield(elem)}
    result
  end

end
