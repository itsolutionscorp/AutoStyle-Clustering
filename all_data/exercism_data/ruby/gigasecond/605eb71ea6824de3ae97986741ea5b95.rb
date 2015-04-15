class Gigasecond
  # convert a gigasecond (a billion seconds) into a number of days:
  GS_IN_DAYS = (10**9)/60/60/24
  # then add that number of days to a given date:
  def self.from (given_date)
    gs_anniversary = given_date + GS_IN_DAYS
  end
end
