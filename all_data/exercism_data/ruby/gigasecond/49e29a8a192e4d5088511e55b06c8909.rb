class Gigasecond
  def self.from(time)
    return Time.at(time.tv_sec + 10**9)
  end
end
