class Gigasecond
  def self.from(date)
    date + gig_sec_to_days
  end

  def self.gig_sec_to_days
    11574
  end

end
