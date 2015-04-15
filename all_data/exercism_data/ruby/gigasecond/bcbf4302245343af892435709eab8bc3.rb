class Gigasecond
  def self.from(time)
    time = time.to_time unless time.is_a?(Time)
    new_time = Time.at(time.to_i + 10 ** 9)
    new_time.to_date
  end
end
