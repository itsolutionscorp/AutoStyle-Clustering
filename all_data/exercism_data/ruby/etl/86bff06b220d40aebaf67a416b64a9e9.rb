class ETL
  def self.transform(legacy)
    scores = Hash.new {|hash, key| hash[key] = 0 }
    legacy.each do |score, letters|
      letters.each do |letter|
        scores[letter.downcase] = score
      end
    end
    scores
  end
end
