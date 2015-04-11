class Gigasecond
  GIGADAYS = 10**9 / 86400

  def self.from(date)
    date + GIGADAYS
  end
end
