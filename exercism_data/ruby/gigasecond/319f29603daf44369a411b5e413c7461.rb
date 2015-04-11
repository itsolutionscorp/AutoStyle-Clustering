class Gigasecond
  def self.from(date)
    add_gs = date.to_time + 10**9
    anniversary = add_gs.to_date
    return anniversary
  end
end
