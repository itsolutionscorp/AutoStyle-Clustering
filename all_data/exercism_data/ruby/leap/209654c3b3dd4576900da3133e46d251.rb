class Year

  def self.leap?(year)
    return false if (year % 4).nonzero?
    return false if (year % 100).zero? && (year % 400).nonzero?

    true
  end

end
