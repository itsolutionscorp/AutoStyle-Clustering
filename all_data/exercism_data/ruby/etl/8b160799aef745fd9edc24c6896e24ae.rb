class ETL
  def self.transform(old)
    old.values.flatten.each_with_object({}) do |e, res|
      res[e.downcase] = old.find { |k, v| v.include? e}.first
    end
  end
end
