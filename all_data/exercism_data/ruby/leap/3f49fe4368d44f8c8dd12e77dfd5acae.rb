class Year
  def self.leap?(year)
    true if (year % 400).zero? || (year % 4).zero? && (year % 100).nonzero?
  end
end
