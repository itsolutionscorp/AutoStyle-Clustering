class Gigasecond

  attr_reader :start_date_in_seconds

  def initialize(start_date)
    @start_date_in_seconds = DateInSeconds.new(start_date)
  end

  def date
    start_date_in_seconds + 1_000_000_000 
  end

end

class DateInSeconds

  attr_reader :date_in_seconds

  def initialize(date)
    @date_in_seconds = date.strftime("%s").to_i
  end

  def +(seconds_to_add)
    Date.strptime((date_in_seconds + seconds_to_add.to_i).to_s, "%s")
  end

end
