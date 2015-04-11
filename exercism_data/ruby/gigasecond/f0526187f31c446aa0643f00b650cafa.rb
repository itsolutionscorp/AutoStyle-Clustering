class Gigasecond < Time

  def self.from(time)

    Gigasecond.at(time + 10 ** 9)

  end

end
