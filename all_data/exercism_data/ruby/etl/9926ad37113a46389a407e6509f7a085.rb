class ETL

  def self.transform(data)
    data.each_with_object({}) do |(k,v), hsh|
      v.each do |new_key|
        hsh[new_key.downcase] = k
      end
    end
  end

end
