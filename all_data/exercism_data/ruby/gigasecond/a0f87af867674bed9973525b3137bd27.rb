class Gigasecond
  VALUE = 10**9

  def self.from(time_of_birth)
    Time.at(time_of_birth + VALUE)
  end
end
