class ETL
  def self.transform(old_score)
    new_score = {}

    old_score.each do |score, letters|
      letters.each { |letter| new_score[letter.downcase] = score }
    end

    new_score
  end
end
