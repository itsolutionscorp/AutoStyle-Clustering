class Array
  def accumulate(&code)
    result = []
    
    self.each do |n| 
      result << code.call(n)
    end

    result
  end
end
