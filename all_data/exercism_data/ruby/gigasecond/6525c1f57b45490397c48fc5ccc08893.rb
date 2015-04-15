class Gigasecond

  attr_reader :start_date

  def initialize(start_date)
    @start_date = start_date
  end

  def date
    date_from_seconds(start_date_in_seconds + 1_000_000_000)
  end

  private

  def start_date_in_seconds
    start_date.strftime("%s").to_i  
  end

  def date_from_seconds(seconds)
    Date.strptime(seconds.to_s, "%s")
  end

end
