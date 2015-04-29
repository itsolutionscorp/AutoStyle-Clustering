class Year
  def self.leap?(year)
    simple_leap?(year) || century_leap?(year)
  end

  def self.simple_leap?(year)
    year.modulo(4).zero? && !year.modulo(100).zero?
  end

  def self.century_leap?(year)
    year.modulo(400).zero?
  end
end
