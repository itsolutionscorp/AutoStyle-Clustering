class ETL
  class << self
    def transform source
      source.each_with_object({}) do |(score, letters), destination|
        invert_letters_and_scores destination, score, letters
      end
    end

    private

    def invert_letters_and_scores destination, score, letters
      letters.map { |letter| destination[letter.downcase] = score }
    end
  end
end
