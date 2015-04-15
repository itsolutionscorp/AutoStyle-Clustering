class Array

  def accumulate(&block)
    [].tap do |new_array|
      for i in self 
        new_array << yield(i)
      end
    end
  end

end
