module ETL
  def self.transform(previous)
    output = []
    previous.each do |key, array|
      array.each {|value| output.push([value.downcase, key]) }
    end
    Hash[output]
  end
end
