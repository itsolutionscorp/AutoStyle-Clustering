class ETL

  def self.transform(data)
    result = {}
    
    data.each do |key, value|
      value.each do |foo|
        result[foo.to_s.downcase] = key
      end
    end

    result
  end
  
end
