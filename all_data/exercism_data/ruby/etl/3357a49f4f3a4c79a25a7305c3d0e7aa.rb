class ETL
  def self.transform(input)
    result = {}
    input.each do |key, value|
      value.each do |array_ele|
        result[array_ele.downcase] = key
      end
    end
    result
  end
end
