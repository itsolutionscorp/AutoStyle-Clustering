class Gigasecond
  def self.from(day)
    if day.class == Date
      day + (10**9/86400)
    elsif day.class == Time
      (day + (10**9)).to_date
    end
  end
end
