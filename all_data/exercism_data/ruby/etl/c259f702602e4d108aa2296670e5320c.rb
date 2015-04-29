class ETL
  def self.transform(old)
  	expected = {}
  	old.each do |point, letters|
	  letters.each do |letter|
	  	expected[letter.downcase] = point
	  end
	end
	expected
  end
end
