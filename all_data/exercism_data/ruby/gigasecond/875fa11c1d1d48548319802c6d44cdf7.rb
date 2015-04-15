class Gigasecond
  def self.from(time)
    time + seconds
  end

  def self.seconds
    10 ** 9
  end
end
