class Gigasecond
  VALUE = 10**9

  def self.from(time)
    Time.at(time + VALUE)
  end
end
