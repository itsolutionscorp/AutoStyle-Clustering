class Gigasecond
  A_GIGASECOND = 1_000_000_000
  def self.from (arg)
    (arg.to_time + A_GIGASECOND).to_date
  end
end
