class ETL
  def self.transform(input)
    input.keys.reduce({}) do | acc, elem |
      input[elem].each { | value | acc[value.downcase] = elem }
      acc
    end
  end
end
