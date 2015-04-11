class ETL
  def self.transform(input)
    input.keys.each_with_object({}) do | elem, acc |
      input[elem].each { | value | acc[value.downcase] = elem }
    end
  end
end
