class Gigasecond
  GS_IN_DAYS = (10**9)/60/60/24
  def self.from (given_date)
    gs_anniversary = given_date + GS_IN_DAYS
    return gs_anniversary
  end
end
