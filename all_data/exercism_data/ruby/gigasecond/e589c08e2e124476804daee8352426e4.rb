class Gigasecond
  CONVERTING_TO_GS = 10**9
  # CONVERTING_TO_GS = 1000000000

  def self.from(start)
    (start.to_time + CONVERTING_TO_GS).to_date
  end
end
