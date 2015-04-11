class Gigasecond

  G_SEC_DAYS = 1*(10**9)/60/60/24

  def self.from(bday)
    bday + G_SEC_DAYS
  end
end
