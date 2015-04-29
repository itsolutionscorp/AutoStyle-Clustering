class Year
  def self.leap?(year)
    divisible_by_400 = year % 400 == 0
    divisible_by_100 = year % 100 == 0
    leap = year % 4 == 0

    case
    when divisible_by_400 then true
    when divisible_by_100 then false
    when leap then true
    end
  end
end
