class Gigasecond
  def self.from date
    seconds_to_date seconds(date) + giga
  end

  private

  def self.giga
    10**9
  end

  def self.seconds date
    date.to_time.to_i
  end

  def self.seconds_to_date epoch_seconds
    Time.at(epoch_seconds).to_date
  end
end
