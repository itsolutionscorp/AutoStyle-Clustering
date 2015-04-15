class ETL
  def self.transform(old)
    result = {}
    old.each do |point, arry|
      arry.each do |letter|
        result[letter.downcase] = point
      end
    end
    result
  end
end
