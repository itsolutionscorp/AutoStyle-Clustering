class Gigasecond
  def self.from d
    Time.at(d.to_i + 10 ** 9)
  end
end
