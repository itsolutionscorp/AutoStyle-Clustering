class ETL
  class << self
    def transform(dataset)
      dataset.inject({}) do |store, item|
        letters = item.last
        point = item.first

        letters.each {|v| store[v.downcase] = point }
        store
      end
    end
  end
end
