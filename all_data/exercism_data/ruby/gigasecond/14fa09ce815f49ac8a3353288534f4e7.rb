module Gigasecond

  def self.from(time)
    gs = time.to_time + 10**9
    gs.to_date
  end

end
