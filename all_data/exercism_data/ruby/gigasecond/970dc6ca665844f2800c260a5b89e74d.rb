class Gigasecond

  GIGASEC = 1000000000

  def self.from date
    date_in_seconds = date.strftime("%s").to_i
    Date.strptime((date_in_seconds + GIGASEC).to_s, "%s")
  end
end
