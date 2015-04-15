class Array
  def accumulate
    self.each_with_object([]) { |e, acc| acc << yield(e) }
  end
end
