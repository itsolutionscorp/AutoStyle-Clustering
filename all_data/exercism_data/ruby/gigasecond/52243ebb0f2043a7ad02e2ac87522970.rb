# Gigasecond calculates the date that someone turned or will celebrate their 1 Gs anniversary.
# A gigasecond is one billion (10**9) seconds.

class Gigasecond
  def self.from birth
    gigasecond_anniv = birth + (10**9)
    if birth.dst? == true && gigasecond_anniv.dst? == true
      gigasecond_anniv -= (60*60)
    end
    gigasecond_anniv
  end

end
