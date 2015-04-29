class Gigasecond

  GIGASECOND_EQUIVALENCE = 10**9

  def self.from(origin_date)
    Time.at(origin_date.to_time.to_i + GIGASECOND_EQUIVALENCE).to_date
  end
end
