class ETL
  def self.transform(hash)
    result = {}
    
    hash.each do |score, array|
      array.each { |letter| result[letter.downcase] = score }
    end

    result
  end
end
