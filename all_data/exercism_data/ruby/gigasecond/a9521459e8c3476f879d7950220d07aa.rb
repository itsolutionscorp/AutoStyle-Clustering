class Gigasecond
  def self.from(birth_time)
    if birth_time.is_a? Time
      birth_time.to_date + (10**9 / 60 / 60 / 24) + 1
    else
      birth_time + (10**9 / 60 / 60 / 24)
    end
  end
end
