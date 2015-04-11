class Array
  def accumulate(&block)
    mapped = []
    each { |item| mapped << yield(item) }
    mapped
  end
end
