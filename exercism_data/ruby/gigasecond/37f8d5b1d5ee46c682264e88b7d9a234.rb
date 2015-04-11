class Gigasecond
  def self.from (in_date)
    ((in_date.to_time)+1_000_000_000).to_date
  end
end
