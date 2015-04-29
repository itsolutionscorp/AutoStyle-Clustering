class ETL

  def self.transform(old)
    result = Hash.new
    old.each do |points, letters|
      letters.each do |letter|
        result[letter.downcase] = points
      end
    end
    result
  end

end
