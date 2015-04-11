class Gigasecond
  def initialize(date)
    @date = date
  end

  def date
    Date.strptime((@date.to_time.to_i + 1000000000).to_s, '%s')
  end
end
