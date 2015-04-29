class Gigasecond

  GigaSeconds = 10**9

  def self.from date
    (date.to_time + GigaSeconds).to_date
  end
end
