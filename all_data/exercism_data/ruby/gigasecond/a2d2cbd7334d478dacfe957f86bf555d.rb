class Gigasecond
  def self.from(cur_time)
    # add 1 billion to the current time in seconds and create a new instance
    # of Time with that as its current value
    return Time.at(cur_time.tv_sec + (10**9))
  end
end
