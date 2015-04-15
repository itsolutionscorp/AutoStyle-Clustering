class Gigasecond

  Giga = 10**9

  def self.from date
    Time.at(Giga + date.to_time.to_i).to_date
  end

end
