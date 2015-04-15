class Gigasecond
  def self.from(birth)
    birth + (10**9 / 60 / 60 / 24)
  end
end
