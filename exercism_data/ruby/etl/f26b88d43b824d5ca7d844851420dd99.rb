class ETL
  def self.transform(old)
    result = {}
    old.each_pair { |n, v| v.each { |letter| result[letter.downcase] = n } }
    result
  end
end
