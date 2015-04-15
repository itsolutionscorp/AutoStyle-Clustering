class Year

  def self.leap?(year)
    y = Integer(year)
    
    return true  if y % 400 == 0
    return false if y % 100 == 0
    (y % 4 == 0)
  end

end
