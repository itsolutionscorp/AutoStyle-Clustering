class Gigasecond

  attr_reader :date_object
  def initialize(date_object)
    @date_object = date_object
  end

  def date
    (date_object + seconds_to_days)
  end

  def seconds_to_days
    seconds = 10**9
    mm, ss = seconds.divmod(60)
    hh, mm = mm.divmod(60)
    dd, hh = hh.divmod(24)
    dd
  end
end
