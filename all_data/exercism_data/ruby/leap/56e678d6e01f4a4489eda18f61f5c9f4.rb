class Year
  def self.leap?(year)
    # if year not divisible by 4 then not leap
    if year % 4 != 0
      return false
    # if year not divisible by 100 then leap
    elsif year % 100 != 0
      return true
    # if year divisible by 400 then leap
    elsif year % 400 == 0
      return true
    # else not leap year
    else
      return false
    end
  end
end
