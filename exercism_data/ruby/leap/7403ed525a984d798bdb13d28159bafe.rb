class Year

  def self.leap?(year)
    case (y = Integer(year))
    when multiple_of?(400) then true
    when multiple_of?(100) then false
    else multiple_of?(4).(y)
    end
  end

  def self.multiple_of?(number)
    lambda { |year| year % number == 0 }
  end

end
