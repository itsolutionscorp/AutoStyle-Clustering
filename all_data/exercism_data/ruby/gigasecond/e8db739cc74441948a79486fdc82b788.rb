class Gigasecond
  GSECS = 1000000000

  def self.from(date)
    date_in_s = date.strftime('%s').to_i
    seconds_to_g = date_in_s + GSECS
    t = Time.at(seconds_to_g).utc
    return Date.parse(t.to_s)
  end
end
