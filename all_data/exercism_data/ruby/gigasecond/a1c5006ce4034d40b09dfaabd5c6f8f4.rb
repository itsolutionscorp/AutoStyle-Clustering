class Gigasecond
  @@sec_in_gs = 10**9
  @@min_in_gs = @@sec_in_gs / 60
  @@hr_in_gs = @@min_in_gs / 60
  @@day_in_gs = @@hr_in_gs / 24
  def self.from(date)
    if date.class == Date
      date + @@day_in_gs
    else
      Date.parse((date + @@sec_in_gs).to_s)
    end
  end
end
