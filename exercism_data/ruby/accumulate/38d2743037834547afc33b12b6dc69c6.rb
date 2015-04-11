class Array
  def accumulate
    each_with_index{|element, index| self[index] = yield element }
  end
end
