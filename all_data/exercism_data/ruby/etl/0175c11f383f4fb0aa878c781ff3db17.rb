class ETL
  class << self
    def transform(scrabble_letter_score_data)
      scrabble_letter_score_data.inject({}) do |result, (score, letters)|
        letters.each{|letter| result.merge!({letter.downcase => score})}
        result
      end
    end
  end
end
