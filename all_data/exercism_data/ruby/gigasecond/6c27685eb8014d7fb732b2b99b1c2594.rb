class Gigasecond

  GIGASECOND = 10**9

  def self.from(t)
   (t.to_time + GIGASECOND).to_date
  end

end
