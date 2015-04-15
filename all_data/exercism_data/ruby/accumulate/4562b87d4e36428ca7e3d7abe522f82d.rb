class Array

  def accumulate
    self.map { |input| yield input }
  end

end
