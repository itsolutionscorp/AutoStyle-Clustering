class Gigasecond

  def self.from(date)
  days_to_gig = 1_000_000_000/86400.to_f
  curr_time = date.to_time
  gs_bday = (curr_time + days_to_gig*60*60*24).to_date
  end

end
