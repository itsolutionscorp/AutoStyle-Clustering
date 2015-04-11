class ETL
  def self.transform(input)
    input.keys.each_with_object({}) { |key, out|
      values = input[key]
      values.each do |v|
        v.downcase!
        out[v] = key
      end
    }
  end
end
