class ETL
  def self.transform(legacy)
    {}.tap do |transformed|
      legacy.each do |legacy_key, legacy_values|
        legacy_values.each do |legacy_value|
          transformed[legacy_value.downcase] = legacy_key
        end
      end
    end
  end
end
