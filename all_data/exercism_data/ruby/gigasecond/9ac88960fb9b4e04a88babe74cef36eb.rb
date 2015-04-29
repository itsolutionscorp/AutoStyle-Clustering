class Gigasecond
  DELTAS = {
    Date => (10**9) / (60 * 60 * 24),
    Time => 10**9
  }

  def self.from(datetime)
    (datetime + DELTAS[datetime.class]).to_date
  end
end
