class ETL
  def self.transform(legacy)
    scores = {}
    legacy.keys.each do |key|
      legacy[key].each do |letter|
        scores[letter.downcase] = key
      end
    end
    scores
  end
end
