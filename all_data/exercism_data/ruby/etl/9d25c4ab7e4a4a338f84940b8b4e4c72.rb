class ETL
  def self.transform(old_data)
    result = Hash.new
    old_data.each do |value, letters|
      letters.each { |letter| result[letter.downcase] = value }
    end
    result
  end
end
