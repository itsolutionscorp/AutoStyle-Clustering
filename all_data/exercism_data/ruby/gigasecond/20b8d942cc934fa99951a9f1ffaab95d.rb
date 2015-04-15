class Gigasecond

  GIGASECOND = 10**9

  def self.from initial
    (initial.to_time + GIGASECOND).to_date
  end

end
