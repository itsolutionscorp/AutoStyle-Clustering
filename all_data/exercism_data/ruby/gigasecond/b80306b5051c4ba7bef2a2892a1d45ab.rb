class Gigasecond

  def self.from(date)
  time = date.to_time
  gs_bday = (time + 1_000_000_000).to_date
  end

end
