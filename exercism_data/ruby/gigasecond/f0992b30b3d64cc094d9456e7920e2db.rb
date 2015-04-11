class Gigasecond
  def self.from(input)
    shifted_time = input.to_time + 10**9
    Date.parse(shifted_time.to_s)
  end
end
