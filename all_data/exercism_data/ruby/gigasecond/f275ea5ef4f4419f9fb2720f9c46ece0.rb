class Gigasecond

GS_IN_DAYS = 10**9 / (24 * 60 * 60)

  def self.from start_date
    return start_date + GS_IN_DAYS
  end

end
