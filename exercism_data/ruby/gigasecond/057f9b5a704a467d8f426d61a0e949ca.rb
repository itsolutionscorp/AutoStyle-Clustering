class Gigasecond
  def self.from(date)
    time = date.to_time if date.respond_to? :to_time
    (time + 10**9).to_date
  end
end
