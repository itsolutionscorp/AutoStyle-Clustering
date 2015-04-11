class Gigasecond

def self.from(bday)
  (bday.to_time + 10**9).to_date
end


end
