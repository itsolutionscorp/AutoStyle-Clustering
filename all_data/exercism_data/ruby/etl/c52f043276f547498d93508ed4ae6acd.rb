module ETL
  def self.transform(old)
    old.each_with_object Hash.new do |(key, value), result|
      value.each do |lone_value|
        result.store lone_value.downcase, key
      end
    end
  end
end
