class Year
  def self.leap?(n)
  	  if n % 4 == 0 and n % 100 != 0
  	  	  return true

	  elsif n % 400 == 0
	  	  return true

	  else return false end
  end
end
