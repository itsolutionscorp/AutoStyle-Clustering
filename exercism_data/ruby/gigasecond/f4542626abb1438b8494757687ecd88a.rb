class Gigasecond 
  def self.from(date)
    timeShift = self.secToDay(ONE_GIGASECOND)

    return date + timeShift
  end

  private
    ONE_GIGASECOND = 10**9
    DAY_IN_SECONDS = 60*60*24

    def self.secToDay(seconds)
      return seconds / (DAY_IN_SECONDS)
    end

end
