class Year  
 
  def self.leap?(date) 
  @date = date 
      if  @date % 4 != 0  
        false
      elsif @date % 100 == 0 && @date % 400 != 0
       false
      else
        true
      end
  end
end
