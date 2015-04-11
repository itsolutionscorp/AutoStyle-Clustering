class Array

  def accumulate(&block)
    return enum_for(:accumulate) unless block_given?
    [].tap do |new_array|
      for i in self 
        new_array << yield(i)
      end
    end
  end

end
