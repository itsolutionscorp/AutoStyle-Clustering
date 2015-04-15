class Year
  def self.leap?(year)
    # Which is the greater evil? Ternary Ops, or hard returns like in iteration 1?
    year % 4 != 0 || (year % 100 == 0 && year % 400 != 0) ? false : true

  end

end
