class ETL
  def self.transform(input)
    input.keys.each_with_object({}) { |key, out|
      invert_values_and_key(key, input[key], out)
    }
  end

  def self.invert_values_and_key(key, values, out)
    values.each do |v|
      v.downcase!
      out[v] = key
    end
  end
end
