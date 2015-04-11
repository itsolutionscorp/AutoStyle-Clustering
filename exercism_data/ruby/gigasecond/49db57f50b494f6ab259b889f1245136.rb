class Gigasecond

  def self.from(date)
    gigasecond = 10**9

    if date.is_a?(Date)
      # there are 86,400 seconds in a day
      (date + Rational(gigasecond, 86400)).to_time.to_date
      # convert to time then back to date to round down to midnight.
    else
      (date + gigasecond).to_date
    end
  end

end
