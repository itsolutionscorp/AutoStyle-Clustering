class ETL
  def self.transform data
    data.each_with_object({}) do |(score, letters), result|
      letters.each do |letter|
        result[letter.downcase] = score
      end
    end
  end
end
