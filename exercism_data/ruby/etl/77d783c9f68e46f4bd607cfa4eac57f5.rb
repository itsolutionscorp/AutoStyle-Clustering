class ETL
  def self.transform(old)
    old.each_with_object({}) do |(old_key, old_value), hash|
      old_value.each do |new_key|
        hash[new_key.downcase] = old_key
      end
    end
  end
end
