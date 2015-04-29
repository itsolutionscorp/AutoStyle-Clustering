class Gigasecond
  def self.from(date_of_birth)
 	date_equiv_of_1Gs = Time.at(1e9).to_date
  	difference_as_date = date_equiv_of_1Gs - Date.new(1970, 1, 1)

  	date_of_birth + difference_as_date
  end
end
