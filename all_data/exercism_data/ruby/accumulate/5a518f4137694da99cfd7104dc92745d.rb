class Array
  def accumulate
    result = []
    each { |elem| result << yield(elem) }
    result
  end
end
