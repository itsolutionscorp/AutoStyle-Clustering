class Year

  def Year.leap?(year)
    if year.modulo(4.0) == 0.0 
      if year.modulo(100.0) == 0.0 and not year.modulo(400.0)  == 0.0
        return false
      else 
	return true
      end
    else 
      return false
    end
   end
end
