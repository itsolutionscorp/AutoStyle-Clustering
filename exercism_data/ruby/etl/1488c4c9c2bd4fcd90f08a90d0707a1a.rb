class ETL
  def self.transform(values)
    values.keys.each_with_object(Hash.new([])) do |key, out|
      values.fetch(key).each do |word|
        out[word.downcase] = key
      end
    end
  end
end
