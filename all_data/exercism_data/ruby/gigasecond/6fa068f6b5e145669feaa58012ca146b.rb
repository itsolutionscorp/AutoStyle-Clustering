class Gigasecond
  def self.from(birth)
    birth + one_billon_seconds
  end

  def self.one_billon_seconds
    10**9
  end
end
