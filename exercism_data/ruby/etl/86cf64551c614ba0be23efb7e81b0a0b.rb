class ETL
  def self.transform(m)
    m.each_with_object({}) do |(k, values), mtemp|
      values.each do |v|
        mtemp[v.downcase] = k
      end
    end
  end
end
