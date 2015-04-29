class ETL
  def self.transform(old)
    old.each_with_object({}) do |(key, values), acc|
      values.each { |value| acc[value.downcase] = key }
    end
  end
end
