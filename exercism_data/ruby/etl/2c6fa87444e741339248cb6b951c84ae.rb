module ETL

  def ETL.transform(score_to_letters)
    letter_to_score = score_to_letters.flat_map do |score, letters|
      letters.map { |letter| [letter.downcase, score] }
    end
    Hash[letter_to_score]
  end

end
