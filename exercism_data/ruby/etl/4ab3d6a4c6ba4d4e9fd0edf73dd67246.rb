class ETL
  def self.transform arg
    return_hash = {}
    arg.each do |key, array_value|
      array_value.each do |value|
        return_hash[value.downcase] = key
      end
    end
    return_hash
  end
end
