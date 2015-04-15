class ETL
  def self.transform(old)
    new = {}
    old.each do |value, letters|
      letters.map(&:downcase).each do |letter|
        new[letter] = value
      end
    end
    new
  end
end
