class Gigasecond
  def initialize (starting_date)
    @starting_date = starting_date

  end

  def date
    gigasecond = 10**9/60/60/24
    @starting_date + gigasecond
  end
end
