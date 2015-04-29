class Year
  def self.leap?(year)
    leap = false

    leap = true  if (year %   4 == 0)
    leap = false if (year % 100 == 0)
    leap = true  if (year % 400 == 0)

    leap
  end
end
