class Gigasecond

  def initialize gsdate
    @gsdate = gsdate
  end

  def date
    return (@gsdate.to_time + 1000000000).to_date
  end

end
