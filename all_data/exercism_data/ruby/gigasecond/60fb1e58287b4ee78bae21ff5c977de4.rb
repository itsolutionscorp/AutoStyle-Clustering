class Gigasecond
  def self.from(date)
    # The + operator for Date adds days
    # so Date + gigasecond in seconds/hours/days
    date + 10**9 / 60 / 60 / 24
  end
end
