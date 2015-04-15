class Gigasecond

  def self.from birth
    Time.at(birth.to_i + (10**9))
  end

end
