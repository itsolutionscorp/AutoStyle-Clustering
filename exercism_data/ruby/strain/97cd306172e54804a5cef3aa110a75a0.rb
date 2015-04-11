class Array

  # alias_method :keep, :select
  # alias_method :discard, :delete_if
  # just kidding

  def keep
    each_with_object([]) do |elem,result|
      result << elem if yield(elem)
    end
  end

  def discard
    each_with_object([]) do |elem,result|
      result << elem unless yield(elem)
    end
  end

end
