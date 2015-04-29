class ETL

  def self.transform(former_value)

    former_value.each_with_object({}) do |(score, letters), new_score|
      letters.map {|letter| new_score[letter.downcase] = score }
    end

  end

end
