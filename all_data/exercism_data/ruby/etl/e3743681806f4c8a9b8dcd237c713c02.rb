class ETL
  def self.transform(legacy)
    legacy.inject({}) do |scores, value|
      key, letters = value
      letters.each { |letter| scores[letter.downcase] = key }
      scores
    end
  end
end
