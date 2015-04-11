module ETL
  extend self

  def transform(extract)
    extract.reduce({}) do |acc, (score, words)|
      words.reduce(acc) { |acc, word| acc.merge(word.downcase => score) }
    end
  end

end
