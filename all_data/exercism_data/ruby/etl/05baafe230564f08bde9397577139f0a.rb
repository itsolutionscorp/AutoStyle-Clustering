class ETL
  def self.transform(old)
    old.map do |score, letters|
      letters.map do |letter|
        [letter.downcase, score]
      end
    end.flatten(1).to_h
  end
end
