class Gigasecond
  def self.from input
    Time.at(input.strftime('%s').to_i + 1000000000).to_date.+(1)
  end
end
