module Year
  def self.leap?(year)
    (year & 3).zero? && (year % 25).nonzero? || (year & 15).zero?
  end
end
