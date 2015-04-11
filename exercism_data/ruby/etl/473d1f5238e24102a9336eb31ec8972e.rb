class ETL
  def self.transform(input)
    input.keys.inject({}) { |out, key|
      out.merge(
        invert_values_and_key(key, input[key])
      )
    }
  end

  def self.invert_values_and_key(key, values)
    values.each_with_object({}) do |v, out|
      out[v.downcase] = key
    end
  end
end
