class Array
  def accumulate
    self.each_with_object([]) do |e, arry|
      arry << yield e
    end
  end
end
