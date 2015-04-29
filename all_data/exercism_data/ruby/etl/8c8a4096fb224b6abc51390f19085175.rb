class ETL
  class << self
    def transform(scrabble_letter_score_data)
      scrabble_letter_score_data.inject({}) do |result, scrabble_score_with_letters|
        score, letters = scrabble_score_with_letters
        letters.each{|v| result.merge!({v.downcase => score})}
        result
      end
    end
  end
end
