module ETL
  def self.transform(old)
    old.each_with_object({}) do |(k, v), h|
      v.each { |val| h[val.downcase] = k }
    end
  end
end
