class ETL
  def self.transform(old)
    new = {}
    old.each do |key,value|
      value.each do |letter|
        new[letter.downcase] = key
      end
    end
    new
  end
end
