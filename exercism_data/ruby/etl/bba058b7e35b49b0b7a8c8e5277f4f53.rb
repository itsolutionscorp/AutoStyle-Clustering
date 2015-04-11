class ETL
  def self.transform(old)
    old.each_with_object({}) do |(k,v), new_hash|
      v.each { |v| new_hash[v.downcase] = k }
    end
  end
end
