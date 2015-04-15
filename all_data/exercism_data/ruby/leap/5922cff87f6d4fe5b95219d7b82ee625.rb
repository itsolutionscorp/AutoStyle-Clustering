class Year
  def self.leap?(year)
    fail ArgumentError, 'Please provide a natural number' unless year == year.to_i && year > 1
    year % 4 == 0 && (year % 400 == 0 || year % 100 != 0)
  end
end
