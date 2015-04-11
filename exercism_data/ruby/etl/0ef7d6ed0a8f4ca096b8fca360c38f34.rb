class ETL
  def self.transform(old_data)
    new_data = {}
    old_data.each do |value, letters|
      letters.each do |letter|
        new_data[letter.downcase] = value
      end
    end
    new_data
  end
end
