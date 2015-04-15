class Gigasecond
  def self.from(a_date)
    gigaseconds         = 10 ** 9
    seconds_since_epoch = a_date.to_time.to_i
    gs_seconds          = seconds_since_epoch + gigaseconds
    gigaseconds_date    = Time.at(gs_seconds).to_date
  end
end
