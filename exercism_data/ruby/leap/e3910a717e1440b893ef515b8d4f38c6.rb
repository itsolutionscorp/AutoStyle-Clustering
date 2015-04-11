class Year

  def Year.leap?(year)
    year_float    = year.to_f
    divisible_4   = year_float / 4
    divisible_100 = year_float / 100
    divisible_400 = year_float / 400

    if divisible_4.to_s[-2, 2] == ".0" then
      if divisible_100.to_s[-2, 2] == ".0" and not divisible_400.to_s[-2, 2] == ".0"
        return false
      else 
	return true
      end
    else 
      return false
    end
   end
end
