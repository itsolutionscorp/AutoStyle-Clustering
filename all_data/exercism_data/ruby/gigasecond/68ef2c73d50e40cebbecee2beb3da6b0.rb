class Gigasecond

  def self.from(date)
  	(date.to_time + self.gigasecs).to_date
  end

	def self.gigasecs
		10**9
	end
end
