class ETL
  def self.transform(old)
    old.inject({}) do |memo, element|
      key = element.shift
      element.flatten.each { |value| memo[value.downcase] = key }
      memo
    end
  end
end
