class Gigasecond < Time

  def self.from(time)

    Gigasecond.at(time.to_i + 10 ** 9)

  end

end
