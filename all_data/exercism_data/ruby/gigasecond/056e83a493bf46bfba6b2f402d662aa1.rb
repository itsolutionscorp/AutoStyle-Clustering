class Gigasecond

  GIGASECOND = 10**9
  SECONDS_IN_DAY = 60*60*24

  def self.from birth
    gs_after_birth = birth + (GIGASECOND/SECONDS_IN_DAY)
  end

end
