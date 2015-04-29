class Gigasecond
  def initialize(date)
    @bday = date
  end

  def date
    add_gigasecond_days = ((((10**9)/60)/60)/24)

    @bday + add_gigasecond_days

  end

end
