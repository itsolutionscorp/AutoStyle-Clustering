class ETL
  def self.transform(input)
    invert = {}

    input.each_key do |key|
      input[key].each do |value|
        invert[value.downcase] = key
      end
    end

    invert
  end
end
