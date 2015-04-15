class Gigasecond < Struct.new(:date)

  def self.from(date)
    new(date).perform
  end

  def perform
    gigasecond = 10**9
    Time.at(seconds_from_date + gigasecond).to_date
  end

  private

  def seconds_from_date
    date.to_time.to_i
  end
end
