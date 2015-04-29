class ETL
  def self.transform(legacy_data)
    legacy_data.each_with_object({}) do |(score, letters), new_data|
      letters.each { |letter| new_data[letter.downcase] = score }
    end
  end
end
