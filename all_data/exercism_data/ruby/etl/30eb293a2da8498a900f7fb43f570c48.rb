class ETL
  def self.transform(data)
    data.each_with_object({}) do |(key, values), result|
      values.each do |value|
        result[value.downcase] = key
      end
    end
  end
end
