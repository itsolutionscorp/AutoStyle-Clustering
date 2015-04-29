class Array
  def accumulate &block
    reduce([]) { |result, element| result << yield(element) }
  end
end
