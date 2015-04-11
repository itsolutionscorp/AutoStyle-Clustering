class Gigasecond

  def self.from birth
    gigasecond = 10**9
    seconds_in_day = 60*60*24
    gs_after_birth_in_days = birth + (gigasecond/seconds_in_day)
  end

end
