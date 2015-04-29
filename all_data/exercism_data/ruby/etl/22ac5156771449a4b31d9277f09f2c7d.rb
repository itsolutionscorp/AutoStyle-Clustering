class ETL
  def self.transform(legacy)
    legacy.inject({}) { |scores, value| convert(scores, value) }
  end

  def self.convert(scores, value)
    key, letters = value
    letters.each { |letter| scores[letter.downcase] = key }
    scores
  end
end
