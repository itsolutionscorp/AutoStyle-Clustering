class ETL

  def self.transform(old_db)
    new_db = {}

    old_db.each do |num_key, letters|
      letters.each do |letter|
        new_db[letter.downcase] = num_key
      end
    end

    new_db
  end

end
