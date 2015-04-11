class ETL
  def self.transform(input)
    input.keys.each_with_object({}) { |key, out|
      out.merge!(
        invert_values_and_key(key, input[key])
      )
    }
  end

  def self.invert_values_and_key(key, values)
    values.each_with_object({}) do |v, out|
      v.downcase!
      out[v] = key
    end
  end
end
