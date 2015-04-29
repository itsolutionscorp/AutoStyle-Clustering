class Gigasecond

  def self.from(birthdate)

    gigasecond = 10**9
    seconds_in_day = 60 * 60 * 24
    
    if birthdate.kind_of? Date
      birthdate + (gigasecond / seconds_in_day)
    else
      time = birthdate + gigasecond
      Date.parse(time.to_s)
    end
    
  end
end
