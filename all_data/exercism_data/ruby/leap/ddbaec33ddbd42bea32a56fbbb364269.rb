class Year
  def self.leap?(year)
    case (y = Integer(year))
    when divisible_by?(400) then true
    when divisible_by?(100) then false
    else divisible_by?(4).(y)
    end
  end

  def self.divisible_by?(number)
    lambda { |year| year % number == 0 }
  end
end
