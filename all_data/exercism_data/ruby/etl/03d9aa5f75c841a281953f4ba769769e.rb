class ETL
  def self.transform old_data
    old_data.each_with_object({}) do |(value, letters), result|
      letters.each do |letter|
        result[letter.downcase] = value
      end
    end
  end
end
