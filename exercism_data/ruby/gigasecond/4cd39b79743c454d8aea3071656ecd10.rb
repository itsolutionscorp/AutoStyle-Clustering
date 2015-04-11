module DateConstants
  SECONDS_IN_DAY = 86400
end

class Gigasecond
  IN_SECONDS = 10**9

  def self.in_days
    IN_SECONDS / DateConstants::SECONDS_IN_DAY
  end

  def self.from(date)
    date + self.in_days
  end
end
