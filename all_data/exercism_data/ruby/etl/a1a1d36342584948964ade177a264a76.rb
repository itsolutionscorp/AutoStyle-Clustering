class ETL
  def self.transform(hash)
    hash.each_with_object({}) do |(key, value), result|
      value.each { |letter| result[letter.downcase] = key }
    end
  end
end
