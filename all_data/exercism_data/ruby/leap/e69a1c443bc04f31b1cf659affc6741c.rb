class Year
  def leap?(years)
    if years % 400 == 0 || years % 4 == 0 && years % 100 != 0
    	'si es'
    	end
    end
end
