class Gigasecond
  GIGASECOND = 10 ** 9

  def self.from(reference_time)
    reference_time + GIGASECOND
  end
end
