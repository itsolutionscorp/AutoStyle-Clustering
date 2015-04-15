# Gigasecond exercise
# Written in 2014 by Benjamin Shyong <hello@benshyong.com>

# Write a program that will calculate the date that someone turned or will celebrate their 1 Gs anniversary.
# A gigasecond is one billion (10**9) seconds.

class Gigasecond
  def self.from(birthdate)
    (birthdate.to_time + 10**9).to_date
  end
end
