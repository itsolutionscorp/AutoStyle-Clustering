class Gigasecond
  GIGASECOND = 1000000000

  def self.from(input_date)
    Date.parse((input_date.to_time + GIGASECOND).to_s)
  end
end
