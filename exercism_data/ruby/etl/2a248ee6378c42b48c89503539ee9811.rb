class ETL
  class << self
    def transform(dataset)
      dataset.each_with_object({}) do |(point, letters), store|
        letters.each {|v| store[v.downcase] = point }
      end
    end
  end
end
