class ETL
  class << self
    def transform(scrabble_letter_score_data)
      scrabble_letter_score_data.inject({}) do |result, (score, letters)|
        letters.inject(result){|accumulator, letter| accumulator.merge!({letter.downcase => score})}
      end
    end
  end
end
