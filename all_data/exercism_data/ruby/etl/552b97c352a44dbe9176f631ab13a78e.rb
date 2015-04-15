class ETL

  def self.transform(old)
    new_hash = {}
    old.each_pair do | points, letters|
      letters.each do |letter|
        new_hash[letter.downcase] = points
      end
    end
    new_hash
  end
end
