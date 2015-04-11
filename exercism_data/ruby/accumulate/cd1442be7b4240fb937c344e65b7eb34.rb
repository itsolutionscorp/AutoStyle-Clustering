class Array

  def accumulate()
    result = []

    self.each do |n|
      temp = yield n
      result << temp
    end

    result
  end

end
