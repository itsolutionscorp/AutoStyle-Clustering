module Gigasecond
  def self.from(date)
    date + 1_000_000_000 / 86_400
  end
end
