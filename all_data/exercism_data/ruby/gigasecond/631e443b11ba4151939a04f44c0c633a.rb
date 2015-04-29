class Gigasecond
  def self.from(time)
    time = time.to_time unless time.is_a?(Time)
    Time.at(time.to_i + 10 ** 9).to_date
  end
end
