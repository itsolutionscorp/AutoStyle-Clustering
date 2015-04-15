class Array
  def accumulate(&block)
    each_with_object([]){|element, agg| agg << yield(element) }
  end
end
