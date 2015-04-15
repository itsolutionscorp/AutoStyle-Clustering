class Gigasecond
  def self.from(birthday)
    gigasecond_in_days = 10**9 / 60 / 60 / 24
    birthday + gigasecond_in_days
  end
end
