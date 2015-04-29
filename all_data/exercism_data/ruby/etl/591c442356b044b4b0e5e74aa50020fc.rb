class ETL
  class << self
    def transform(dataset)
      dataset.each_with_object({}) do |item, store|
        letters = item.last
        point = item.first

        letters.each {|v| store[v.downcase] = point }
      end
    end
  end
end
