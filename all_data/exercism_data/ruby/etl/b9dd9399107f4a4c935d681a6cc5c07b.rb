module ETL
  def self.transform(extract)
    extract.flat_map do |score, words|
      words.map { |word| { word.downcase => score } }
    end.reduce(&:merge)
  end
end
