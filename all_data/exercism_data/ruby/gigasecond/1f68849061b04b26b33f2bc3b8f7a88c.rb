class Gigasecond
	GIGASECOND = 1E9

  def self.from(reference_time)
    reference_time + GIGASECOND
  end
end
