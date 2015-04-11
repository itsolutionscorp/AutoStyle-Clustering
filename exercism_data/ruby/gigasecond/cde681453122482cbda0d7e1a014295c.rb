class Gigasecond
  SEC_IN_GS = 10**9

  def self.from(start_date)
    start_date + SEC_IN_GS
  end
end
