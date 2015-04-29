class Year
  def self.leap?(year)
    if year % 4 == 0 then
      if year % 100 == 0 then
        if year % 400 == 0 then
          true
        else
          false
        end
      else
        true
      end
    else
      false 
    end
  end
end
