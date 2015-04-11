class ETL
  def self.transform(legacy_data)
    legacy_data.inject({}) do |new_data, (score, letters)|
      letters.each { |letter| new_data[letter.downcase] = score }
      new_data
    end
  end
end
