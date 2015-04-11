class ETL
  def self.transform(old)
    new = {}
    old.each do |point, words|
      words.each do |word|
        new[word.downcase] = point
      end
    end
    new
  end
end
