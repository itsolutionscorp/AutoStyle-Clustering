class Year
  def self.leap?(year)
    # return keyword necessary for early returns
    return true if year % 400 == 0
    return true if year % 4 == 0 && year % 100 != 0
    false
  end
end
