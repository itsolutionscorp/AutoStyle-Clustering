class Gigasecond
  GIGASECOND = 10**9

  def self.from(date)
    new(date)
  end

  def date
    date_plus_one_gigasecond
  end

  private

  def initialize(date)
    @date = date.to_time
  end

  def date_plus_one_gigasecond
    seconds_to_date(date_to_seconds(@date) + GIGASECOND)
  end

  def seconds_to_date(seconds)
    Time.at(seconds).to_date
  end

  def date_to_seconds(date_obj)
    date_obj.to_i
  end
end
