class ETL
  def self.transform(hash={})
    map = {}
    
    hash.each_pair do |score, letters|
      letters.each do |letter|
        map[letter.downcase] = score
      end
    end

    map
  end
end
