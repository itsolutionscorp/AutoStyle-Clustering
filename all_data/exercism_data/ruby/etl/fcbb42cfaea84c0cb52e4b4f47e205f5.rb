class ETL
  def self.transform(data)
    output = {}

    data.each do |k,v|
      v.each do |letter|
        output[letter.downcase] = k
      end
    end

    output
  end
end
