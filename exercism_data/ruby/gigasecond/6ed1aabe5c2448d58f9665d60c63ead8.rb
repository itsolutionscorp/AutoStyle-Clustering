class Gigasecond

  @@one_gigasecond = 10**9

  def self.from( date )
    (date.to_time + @@one_gigasecond).to_date
  end

end
