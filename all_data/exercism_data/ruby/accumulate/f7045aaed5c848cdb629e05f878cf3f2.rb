class Array
  def accumulate
    each.with_object([]) {|x, result| result << (yield x)}
  end
end
