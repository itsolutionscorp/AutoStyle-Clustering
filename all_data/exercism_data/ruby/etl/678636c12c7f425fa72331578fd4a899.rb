class ETL

  def self.transform(old)
    old.inject({}) do |memo, element|
      key = element.shift
      element.flatten.each do |value|
        memo[value.downcase] = key
      end
      memo
    end
  end

end
