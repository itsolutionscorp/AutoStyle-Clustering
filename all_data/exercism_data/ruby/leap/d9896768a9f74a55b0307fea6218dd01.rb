class Year

  def Year.leap?(year)
    if year.to_f.modulo(4.0) == 0.0 then
      if year.to_f.modulo(100.0) == 0.0 and not year.to_f.modulo(400.0)  == 0.0
        return false
      else 
	return true
      end
    else 
      return false
    end
   end
end
