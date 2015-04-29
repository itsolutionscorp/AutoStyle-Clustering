class Array
  def accumulate
    reduce([]) { |result, elem| result << yield(elem) }
  end
end
