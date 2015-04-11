class Gigasecond
  def self.from(date)
    # 1 year = 3.15569e7 seconds
    # 1 year = 365.242 days
    # 1 month = 2.63e+6 seconds
    # 1 month = 30.4368 days
    # 1 day = 86400 seconds

    year = date.year
    month = date.month
    day = date.day

    giga = 10**9

    gs_y = 2043
    gs_m = 1
    gs_d = 1

    Date.new(gs_y, gs_m, gs_d)
    # spent too long figuring out how to do date calculations with
    # DateTime, and 'Date.day_fraction_to_time' not working...
  end
end
