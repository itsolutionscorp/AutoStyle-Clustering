class Gigasecond

  def initialize gsdate
    @gsdate = gsdate.to_time
  end

  def date
    return (@gsdate + 1_000_000_000).to_date
  end

end
