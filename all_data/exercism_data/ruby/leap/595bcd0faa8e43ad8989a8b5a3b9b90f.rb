class Year
  def self.leap?(year)
    true if year % 400 == 0
    false if year % 100 == 0
    true if year % 4 == 0
  end
end
