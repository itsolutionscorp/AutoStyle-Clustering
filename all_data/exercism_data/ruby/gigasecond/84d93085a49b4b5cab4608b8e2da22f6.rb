class Gigasecond

  GIGASECOND = 10**9

  def self.from(date)
    case date.class.name
      when "Date" then date + days
      when "Time" then (date + GIGASECOND).to_date
    end
  end

  def self.days
    (GIGASECOND / 60 / 60 / 24)
  end
end
