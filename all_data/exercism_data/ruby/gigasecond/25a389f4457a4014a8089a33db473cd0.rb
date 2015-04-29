class Gigasecond < Struct.new(:date)

  GIGASECOND = 10**9

  def self.from(date)
    new(date).perform
  end

  def perform
    Time.at(seconds_from_date + GIGASECOND).to_date
  end

  private

  def seconds_from_date
    date.to_time.to_i
  end
end
