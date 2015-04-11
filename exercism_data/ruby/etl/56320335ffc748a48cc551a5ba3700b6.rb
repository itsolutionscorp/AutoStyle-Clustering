class ETL

  def self.transform(old)
    new = {}
    old.each do |score,letters|
      letters.each do |letter|
        new[letter.downcase] = score
      end
    end
    new
  end

end
