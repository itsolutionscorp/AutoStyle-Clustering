class Year < Date
def leap?(y)
     
     if y % 4  == 0
      return true
     else 
      return false
     end

  end


end
