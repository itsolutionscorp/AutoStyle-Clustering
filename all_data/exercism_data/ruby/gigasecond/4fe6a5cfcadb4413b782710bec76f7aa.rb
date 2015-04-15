class Gigasecond

  def self.from(d)
    (Time.parse(d.to_s) + 1_000_000_000).to_date
  end

end
