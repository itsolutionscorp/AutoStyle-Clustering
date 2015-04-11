class Gigasecond

  def self.from initial
    (initial.to_time + 10**9).to_date
  end

end
