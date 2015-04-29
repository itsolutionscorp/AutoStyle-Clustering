class Gigasecond
  def self.from year, month, day
    birthday = Time.mktime(year, month, day)
    gigabirth = birthday + 10**9
  end
end
