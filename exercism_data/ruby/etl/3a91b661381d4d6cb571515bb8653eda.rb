class ETL

  def self.transform(old)
    old.each_with_object({}) do |(key, value), hash|
      value.map {|content| hash[content.downcase] = key}
    end
  end

end
