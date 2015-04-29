class Gigasecond
  def self.from(birthdate)
    unixified = birthdate.to_time
    annivGigas = unixified + 10**9
    annivDate = Time.at(annivGigas).to_date
    return annivDate
  end
end
