class Gigasecond
  def self.from(date)
    # one gigasecond, represented in days
    one_gigasecond = 1_000_000_000 / 60 / 60 / 24
    date + one_gigasecond
  end
end
