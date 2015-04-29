class ETL
  def self.transform data
    result = {}
    data.each do |score, letters|
      letters.each do |letter|
        result[letter.downcase] = score
      end
    end
    result
  end
end
