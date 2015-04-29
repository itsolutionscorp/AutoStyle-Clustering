class ETL
  class << self
    def transform(data)
      data.inject({}) do |result, value_pair|
        value_pair[1].each{|v| result.merge!({v.downcase => value_pair[0]})}
        result
      end
    end
  end
end
