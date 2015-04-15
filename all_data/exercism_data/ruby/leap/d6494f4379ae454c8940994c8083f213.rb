class Year  
  def self.leap?(date) 
      if  date % 4 != 0  
        false
      elsif date % 100 == 0 && date % 400 != 0
       false
      else
        true
      end
  end
end


#class Year
    #def self.leap?(year)
          #year % 400 == 0 or ( year % 100 != 0 and year % 4 == 0 )
    #end
#end 
