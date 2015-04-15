class Gigasecond
  def self.from(date)
    date.class == Time ? (date + 10**9).to_date : date + 10**9/60/60/24
  end
end
