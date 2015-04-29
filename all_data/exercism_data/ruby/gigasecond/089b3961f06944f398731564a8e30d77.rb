class Gigasecond

  GIGASECOND = 10**9
  GIGASECOND_IN_DAYS = (GIGASECOND / 60 / 60 / 24)

  def self.from(date)
    case date.class.name
      when "Date" then date + GIGASECOND_IN_DAYS
      when "Time" then (date + GIGASECOND).to_date
    end
  end

end
