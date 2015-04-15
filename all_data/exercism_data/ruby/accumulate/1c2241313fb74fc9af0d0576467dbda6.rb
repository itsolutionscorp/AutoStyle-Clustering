class Array
  def accumulate
    self.map {|x| yield x }
  end
end
