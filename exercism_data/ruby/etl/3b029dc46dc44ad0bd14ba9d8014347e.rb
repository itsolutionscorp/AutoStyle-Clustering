class ETL
  def self.transform hash
    Hash.new(0).tap do |result|
      hash.each do |count, words|
        words.each do |word|
          result[word.downcase] += count
        end
      end
    end
  end
end
