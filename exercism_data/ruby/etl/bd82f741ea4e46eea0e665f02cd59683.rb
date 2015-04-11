class ETL

  def self.transform(old)
    scrabble_db = {}
    old.each do |score, letter|
      letter.each do |new|
        scrabble_db[new.downcase] = score
      end
    end
    scrabble_db
  end

end
