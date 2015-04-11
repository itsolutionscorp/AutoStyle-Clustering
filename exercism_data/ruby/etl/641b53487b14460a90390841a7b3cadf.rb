class ETL
  def self.transform(old)
    old.each_with_object({}) do |entry,hash|
      key, values = entry
      values.each do |value|
        hash[value.downcase] = key
      end
    end
  end
end
