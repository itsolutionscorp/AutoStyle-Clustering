class Gigasecond

  def self.from(date)
    (DateTime.new(date.year, date.month, date.day, 0, 0, 0) + days_in_a_gigasecond).to_date
  end

  def self.days_in_a_gigasecond
    11574.074074074
  end

end
