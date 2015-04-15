class Year
  def self.leap?(year)
    if year % 400 == 0
      return true
    elsif year % 100 == 0
      return false
    else year % 4 == 0
      true
    end
  end
end
