class ETL

  def self.transform(old)
    data = Hash.new
    old.keys.each do |key|
      old[key].each do |value|
        value.downcase!
        data[value] = key
      end
    end
    data
  end
end
