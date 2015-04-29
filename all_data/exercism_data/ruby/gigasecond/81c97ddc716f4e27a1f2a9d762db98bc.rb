class Gigasecond
  def self.from(input)
    (input.to_time + 10 ** 9).to_date
  end
end
