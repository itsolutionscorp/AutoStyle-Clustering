class Gigasecond

  GIGASECOND = 10 ** 9

  def self.from(t)
    Time.at(t.to_i + GIGASECOND)
  end

end
