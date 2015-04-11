class Gigasecond


Gigadays = (10**9)/(86400)

  def self.from(date)
    date + Gigadays
  end

end
