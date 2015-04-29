module ETL
  module_function

  def transform(letters_for_scores)
    Hash[
      letters_for_scores.flat_map do |score, letters|
        letters.map { |letter| [letter.downcase, score] }
      end
    ]
  end
end
