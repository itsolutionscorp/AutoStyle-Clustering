class Gigasecond

  @gigasecond = 10**9

  def self.from(time)
    time + @gigasecond
  end

end
