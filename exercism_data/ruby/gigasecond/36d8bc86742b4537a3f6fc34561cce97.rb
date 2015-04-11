class Gigasecond
  def self.from( date )
    # 11574 are the number of days (1**9 / 3600 / 24)
    date + 11574
  end
end
