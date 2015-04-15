class Gigasecond
  def self.from(birthdate)
    gigas = 10**9
    gigas_to_days = gigas / 60 / 60 / 24
    if birthdate.is_a? Date  
      birthdate + gigas_to_days
    else
      if birthdate.hour > 1.7 # Remainder seconds converted to hours
        date = birthdate.to_date + 1
        date + gigas_to_days
      else 
        date = birthdate.to_date
        date + gigas_to_days
      end
    end
  end
end
