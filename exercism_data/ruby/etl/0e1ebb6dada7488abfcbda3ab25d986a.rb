class ETL
  def self.transform(input)
    info = {}
    input.each do |key, value|
      value.each do |v|
        info[v.downcase] = key
      end
    end
    info
  end
end
