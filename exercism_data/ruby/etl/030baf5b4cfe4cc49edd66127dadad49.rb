class ETL
  class << self
    def transform(dataset)
      dataset.inject({}) do |store, item|
        values = item.last
        point = item.first

        values.each {|v| store[v.downcase] = point }
        store
      end
    end
  end
end
