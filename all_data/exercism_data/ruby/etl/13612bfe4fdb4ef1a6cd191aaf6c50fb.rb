class ETL
  def self.transform(old)
    old.each_with_object({}) do | (old_key,old_val), hsh |
      old_val.each do |new_key|
        hsh[new_key.downcase] = old_key
      end
    end
  end
end
