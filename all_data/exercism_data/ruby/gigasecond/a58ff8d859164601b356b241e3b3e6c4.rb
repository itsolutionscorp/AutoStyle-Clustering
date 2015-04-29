class Gigasecond
  def self.from(birthdate)
    unixified = birthdate.to_time.to_i
    annivGigas = unixified + 1000000000
    annivDate = Time.at(annivGigas).to_date
    return annivDate
  end
end
