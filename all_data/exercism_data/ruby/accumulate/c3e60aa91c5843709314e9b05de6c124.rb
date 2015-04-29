class Array
  def accumulate
    self.each_with_index { |x, i| self[i] = yield x }
    # self.map! { |x| yield x }
  end
end
