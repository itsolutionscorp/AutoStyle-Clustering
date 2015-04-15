class Year
  def self.leap?(year)
    (year % 4).zero? && ((year % 400).zero? || !(year % 100).zero?)
  end
end
