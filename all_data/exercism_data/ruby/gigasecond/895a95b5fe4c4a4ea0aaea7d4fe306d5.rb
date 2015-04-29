class Gigasecond
  def self.from(birth)
    birth + (10**9 / 86400)
  end
end
