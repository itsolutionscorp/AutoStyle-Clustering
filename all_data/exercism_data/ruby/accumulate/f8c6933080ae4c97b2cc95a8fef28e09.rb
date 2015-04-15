class Array
  def accumulate
    each.with_object([]) {|b,r| r << yield(b) }
  end
end
