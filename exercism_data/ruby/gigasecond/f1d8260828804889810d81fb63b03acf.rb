class Gigasecond

def self.from(birthdate)
  days = (10**9/60/60/24)
  if birthdate.class == Date
    birthdate + days
  elsif birthdate.class == Time
    birthdate.to_date + days + 1
  end
end

end
