class ETL
  def self.transform old
    old.each_with_object({}) do |(key,values), out|
      values.each do |value|
        out[value.downcase] = key
      end
    end
  end
end
