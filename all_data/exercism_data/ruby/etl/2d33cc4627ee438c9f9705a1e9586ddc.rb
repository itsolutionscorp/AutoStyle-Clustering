class ETL
  def self.transform(values)
    values.keys.each_with_object(Hash.new([])) do |k, out|
      values.fetch(k).each do |w|
        out[w.downcase] = k
      end
    end
  end
end
