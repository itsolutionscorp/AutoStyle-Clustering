class Gigasecond
  def initialize(birthday)
    @birthday = birthday
  end
  
  def seconds_to_full_days(seconds)
    (seconds / 60 / 60 / 24).floor
  end
  
  def date
    @birthday + seconds_to_full_days(1e9)
  end
end
