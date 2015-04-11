class Gigasecond
  attr_reader :starting_date

  def initialize(starting_date)
    @starting_date = starting_date
  end

  def date
    starting_date + 11_574 # Days per gigasecond
  end
end
