class Gigasecond
  require 'date'

  # This test will return the date of the anniversary of 1 Gigasecond of age.
  # Input your birthday, find out when you're a gigasecond old.
  GS = 1_000_000_000

  def self.from(date)
    date_as_sec = date.to_time.to_i
    gs = date_as_sec + GS
    Time.at(gs).to_date
  end

end
