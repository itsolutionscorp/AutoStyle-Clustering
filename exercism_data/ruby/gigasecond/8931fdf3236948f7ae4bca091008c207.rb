class Gigasecond < Struct.new(:birth)
  GIGASECOND = 1000000000

  def self.from(birth)
    new(birth).compute_gigasecond
  end

  def compute_gigasecond
    birth_time = birth.to_time
    gigasecond_time = birth_time + GIGASECOND
    gigasecond_time.to_date
  end
end
