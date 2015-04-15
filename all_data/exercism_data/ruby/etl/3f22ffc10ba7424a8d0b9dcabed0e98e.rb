class ETL

  def self.transform(old_db)    
    old_db.each_with_object({}) do |old_record, new_db|
      score, letters = old_record
      letters.each do |letter|
        new_db[letter.downcase] = score
      end
    end    
  end

end
