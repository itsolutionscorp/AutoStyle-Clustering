class ETL
  def self.transform(input)
    input.keys.each_with_object({}) { |key, out|
      out.merge(
        invert_values_and_key(key, input[key])
      )
    }
  end

  def invert_values_and_key(key, values)
    values.each_with_object { |v, out|
      v.downcase!
      out[v] = key
    }
  end
end
