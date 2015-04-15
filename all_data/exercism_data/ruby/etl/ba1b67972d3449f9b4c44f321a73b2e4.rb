module ETL
  def self.transform(extract)
    extract.map do |score, words|
      Hash[words.map { |word| [word.downcase, score] }]
    end.reduce(&:merge)
  end
end
