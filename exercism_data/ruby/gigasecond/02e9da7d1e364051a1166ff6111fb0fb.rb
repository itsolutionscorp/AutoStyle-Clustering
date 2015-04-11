class Gigasecond

  GIGASECOND = 10**9

  def self.from date
    aniversary = date.to_time.to_i + GIGASECOND

    Time.at( aniversary ).to_date
  end

end
