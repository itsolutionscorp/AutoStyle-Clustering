class ETL

  class << self

    def transform(data)
      data.each_with_object({}) do |(score,words), rule_book|
        words.each do |word|
          rule_book[word.downcase] = score
        end
      end
    end

  end

end
