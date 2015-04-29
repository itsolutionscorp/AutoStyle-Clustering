class ETL

  def self.transform(old)
    new_hash = {}
    old.each do |score, letter|
      letter.each do |convert|
        new_hash[convert.downcase] = score
      end
    end
    new_hash
  end
end
