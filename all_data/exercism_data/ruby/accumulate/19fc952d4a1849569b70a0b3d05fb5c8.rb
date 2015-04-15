class Array
  def accumulate
    each.with_index { |item, index| self[index] = yield item }
  end
end
