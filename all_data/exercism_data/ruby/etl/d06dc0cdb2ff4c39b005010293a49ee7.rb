class ETL

  def self.transform(input)
    input.inject(Hash.new) do |hash, (key, values)|
        values.each { |value| hash[value.downcase] = key }
        hash
    end
  end
end
