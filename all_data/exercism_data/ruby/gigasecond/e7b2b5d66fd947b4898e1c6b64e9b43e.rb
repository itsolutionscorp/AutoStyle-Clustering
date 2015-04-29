class Gigasecond
  def self.from date
    seconds_since_epoc = date.strftime("%s").to_i
    gigaseconds_since_epoc = 10**9
    
    giga_birthday = seconds_since_epoc + gigaseconds_since_epoc

    Date.strptime(giga_birthday.to_s, "%s")
  end
end
