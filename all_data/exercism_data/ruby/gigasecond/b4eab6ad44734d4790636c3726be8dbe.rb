module Gigasecond

  ONE_GIGASECOND = 1_000_000_000

  def self.from time
    time + ONE_GIGASECOND
  end
end
