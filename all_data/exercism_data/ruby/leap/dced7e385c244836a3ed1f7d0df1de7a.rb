class Year
  def self.leap?(year)
    fail ArgumentError, 'Please provide a natural number' unless check?(year)
    year % 4 == 0 && (year % 400 == 0 || year % 100 != 0)
  end

  def self.check?(y)
    y == y.to_i && y > 1
  end
end
