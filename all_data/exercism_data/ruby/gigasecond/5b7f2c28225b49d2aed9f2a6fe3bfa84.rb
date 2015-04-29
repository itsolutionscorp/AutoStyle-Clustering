class Gigasecond
  GIGASECONDS = 10**9

  def self.from(date)
    seconds = DatePresenter.new(date).seconds + GIGASECONDS
    Date.strptime(seconds.to_s, '%s')
  end
end

class DatePresenter
  def initialize(date)
    @date = date
  end

  def seconds
    @date.strftime('%s').to_i
  end
end
