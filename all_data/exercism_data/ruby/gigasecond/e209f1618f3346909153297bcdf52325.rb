class Gigasecond < Struct.new(:date)

  def self.from(date)
    new(date).perform
  end

  def perform
    Time.at(date.to_time.to_i + 10**9).to_date
  end
end
