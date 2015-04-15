class ETL
  def self.transform(data)
    data.each_with_object({}) do |(score, chars), hash|
      chars.each{ |char| hash[char.downcase] = score }
    end
  end
end
