class Array
  def accumulate(&block)
    # `reduce` swiss army knife to the rescue!
    reduce([]) { |collection,element| collection << yield(element) }
  end
end
