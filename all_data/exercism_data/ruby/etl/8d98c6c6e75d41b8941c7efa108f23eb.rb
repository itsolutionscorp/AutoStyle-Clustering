class ETL
  def self.transform(old)
    transformed = {}
    old.each_key do |key|
      old[key].each do |char|
        transformed[char.downcase] = key
      end
    end
    transformed
  end
end
