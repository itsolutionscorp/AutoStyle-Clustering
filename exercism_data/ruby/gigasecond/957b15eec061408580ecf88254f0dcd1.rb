class Gigasecond

  def self.from(born_date)

  	if born_date.year < 1969
			return born_date + 10**9 - 60*60
		else
			return born_date + 10**9
		end

  end

end
