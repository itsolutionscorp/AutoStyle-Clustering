class Gigasecond
  GIGASECOND = 10**9

  def self.from date
    date_seconds = date.strftime('%s').to_i
    Date.strptime((date_seconds + GIGASECOND).to_s, "%s")
  end
end
