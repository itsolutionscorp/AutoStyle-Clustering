class Gigasecond
  def self.from(arg)
    ((arg.to_time + 1000000000)).to_date
  end
end
