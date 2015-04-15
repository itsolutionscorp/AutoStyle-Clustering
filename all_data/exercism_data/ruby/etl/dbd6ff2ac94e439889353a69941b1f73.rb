class ETL

  def self.transform(data)
    some_method(data)
  end

  def self.some_method(data)
    new_data = {}
    data.each do |key, value|
      value.each {|val| new_data[val.downcase] = key}
    end
    new_data
  end

end
