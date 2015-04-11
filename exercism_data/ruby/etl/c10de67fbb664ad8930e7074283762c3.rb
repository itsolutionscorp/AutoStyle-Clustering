class ETL

  def self.transform(data)
    new_data = {}
    data.each_key{ |key| old_to_new(data, key, new_data) }
    return new_data
  end

  def self.old_to_new(data, key, new)
    data[key].map{ |new_key| new[new_key.downcase] = key }
  end

end
