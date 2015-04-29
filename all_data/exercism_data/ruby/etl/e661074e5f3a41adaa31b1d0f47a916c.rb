class ETL
  def self.transform(legacy)
    legacy.each_with_object({}) do |old, transformed|
      old[1].each { |k| transformed[k.downcase] = old[0] }
    end
  end
end
