class ETL
  def self.transform(old)
    old.inject({}) do |acc, entry|
      entry.last.each do |element|
        acc[element.downcase] = entry.first
      end
      acc
    end
  end
end
