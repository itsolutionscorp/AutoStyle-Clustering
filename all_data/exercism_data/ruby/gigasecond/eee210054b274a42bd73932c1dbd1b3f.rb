module Gigasecond
  def self.from(a)
    (a.to_time + 1_000_000_000).to_date
  end
end
