class Gigasecond
  def self.from(date)
    gigaseconds_in_days = ( 10**7 ) / 864
    date + gigaseconds_in_days
  end
end
