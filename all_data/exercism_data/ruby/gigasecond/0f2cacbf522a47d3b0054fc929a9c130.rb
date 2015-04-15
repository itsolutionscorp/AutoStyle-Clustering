class Gigasecond

  def self.from(time_stamp)
    (time_stamp.to_time + 10**9).to_date
  end

end
