class ETL
  def self.transform(data)
    data.each_with_object({}) do |(key, values), hash|
      values.each do |value|
        hash[value.downcase] = key
      end
    end
  end
end
