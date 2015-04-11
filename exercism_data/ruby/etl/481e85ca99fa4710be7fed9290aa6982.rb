class ETL

  def self.transform(old_db)
    new_db = {}
    old_db.each do |score, letters|
      letters.each do |letter|
        new_db[letter.downcase] = score
      end
    end
    new_db
  end

end
