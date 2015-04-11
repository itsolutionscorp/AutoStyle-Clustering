class Array
  
  def accumulate()
    ret = []
    self.each do |n|
      ret << yield(n)
    end  
    ret
  end  
  
end  
