class ETL

  def self.transform(old_score)

    old_score.each_with_object({}) do |(score, letters), new_score|
      letters.map {|letter| new_score[letter.downcase] = score }
    end

  end

end
