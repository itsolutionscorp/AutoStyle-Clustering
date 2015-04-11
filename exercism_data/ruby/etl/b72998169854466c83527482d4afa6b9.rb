module ETL
  def self.transform(old)
    result = {}
    old.each do |score, letters|
      letters.each do |letter|
        result[letter.downcase] = score
      end
    end
    result
  end
end
