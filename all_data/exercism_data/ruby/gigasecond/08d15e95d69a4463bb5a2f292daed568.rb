class Gigasecond

  def self.from (d)
    (d.to_time + 10**9).to_date
  end

end
