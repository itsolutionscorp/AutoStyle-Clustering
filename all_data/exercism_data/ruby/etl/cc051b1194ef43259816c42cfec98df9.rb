class ETL
  def self.transform(input)
    transformed = {}

    if input.size == 1
      if input.values.first.size == 1
        transformed_value = input.keys.first
        transformed_key = input.values.first.first.downcase
        transformed[transformed_key] = transformed_value
        return transformed
      elsif input.values.first.size > 1
        transformed_value = input.keys.first
        input.values.first.each do |value|
          transformed[value.downcase] = transformed_value
        end
        return transformed
      end
    else
      input.each do |element|
        transformed_value = element.first
        element[1].each do |value|
          transformed[value.downcase] = transformed_value
        end
      end
      return transformed
    end
  end
end
