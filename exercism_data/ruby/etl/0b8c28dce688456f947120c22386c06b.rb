module ETL
  def self.transform(letters_by_score)
    score_by_letter = {}
    letters_by_score.each_pair do |score, letters|
      letters.each do |letter|
        score_by_letter[letter.downcase] = score
      end
    end
    score_by_letter
  end
end
