#Year class
class Year
  def self.leap?(anio)
    if anio % 4 == 0 
      if anio % 100 != 0
        true
      elsif anio % 400 == 0
        true
      else
        false
      end
    else
     false
    end
   end
end
