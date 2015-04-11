class Gigasecond
  attr_accessor :birthdate

  def initialize(birthdate)
    @birthdate = birthdate
  end

  def birthtime
    @birthtime ||= birthdate.to_time
  end

  def time
    @time ||= Time.at(birthtime + 1_000_000_000)
  end

  def date
    @date ||= time.to_date
  end

  def year
    date.year
  end
end
