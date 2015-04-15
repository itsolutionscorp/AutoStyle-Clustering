class Array

  def accumulate
    each_with_index do |n,i|
      self[i] = yield n
    end
  end

end
