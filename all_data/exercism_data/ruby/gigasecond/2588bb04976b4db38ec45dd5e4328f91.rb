
class Gigasecond

  def initialize(date)
    @gs_time = date.to_time
  end

  def +(gs)
    @gs_time + (gs * 10**9)
  end

  def gigaversary(gs=1)
    self + gs
  end

  def date
    gigaversary.to_date
  end

end
