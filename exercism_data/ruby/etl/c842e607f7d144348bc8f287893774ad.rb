class ETL
  def self.transform(old)
    newer = {}
    old.each do |key, letters|
      letters.each do |letter|
        newer[letter.downcase] = key
      end
    end
    newer
  end
end
