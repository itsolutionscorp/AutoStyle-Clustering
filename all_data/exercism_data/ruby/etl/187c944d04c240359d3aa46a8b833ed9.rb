class ETL

  def self.transform(old_hash)
    old_hash.each_with_object({}) do |(key, value), hash|
      value.each do |value|
        hash[value.downcase] = key
      end
    end
  end

end
