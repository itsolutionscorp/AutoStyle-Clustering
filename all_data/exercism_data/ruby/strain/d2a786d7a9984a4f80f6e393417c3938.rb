class Array
  
  def keep
    ret = []
    self.each {|n| ret << n if yield(n) }
    ret
  end 
  
  def discard
    ret = []
    self.each {|n| ret << n unless yield(n) }
    ret
  end  
   
end  
