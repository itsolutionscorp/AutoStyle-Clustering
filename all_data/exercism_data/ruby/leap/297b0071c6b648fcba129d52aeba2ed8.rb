class Year
  def self.leap?(year)
    if year % 4 != 0 then return false end
    if year % 100 == 0 and year % 400 != 0 then return false end
    true
  end
end
