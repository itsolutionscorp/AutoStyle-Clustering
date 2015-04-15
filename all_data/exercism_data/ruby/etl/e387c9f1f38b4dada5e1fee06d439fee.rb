class ETL

  def self.transform(data)
    transformed_data = {}
    data.each do |key, value|
      value.each {|val| transformed_data[val.downcase] = key}
    end
    transformed_data
  end

end
